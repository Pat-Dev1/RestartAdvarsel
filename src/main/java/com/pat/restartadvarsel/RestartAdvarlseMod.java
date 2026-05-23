package com.pat.restartadvarsel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.text.Text;
import net.minecraft.server.MinecraftServer;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RestartAdvarlseMod implements ModInitializer {
    private static final ZoneId OSLO_TZ = ZoneId.of("Europe/Oslo");
    private static final int[] WARNING_TIMES = {30, 15, 10, 5, 2}; // minutter
    
    private static long lastRestartWarning30Sec = 0;
    private static long[] lastRestartWarnings = new long[WARNING_TIMES.length];
    private static long lastShutdownWarning30Sec = 0;
    private static long[] lastShutdownWarnings = new long[WARNING_TIMES.length];

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(this::onServerTick);
    }

    private void onServerTick(MinecraftServer server) {
        LocalDateTime now = LocalDateTime.now(OSLO_TZ);
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        long currentTime = System.currentTimeMillis();

        // RESTART: 16:00
        checkAndWarn(server, hour, minute, second, currentTime, 16, 0, "RESTART", lastRestartWarnings, "lastRestartWarning30Sec");

        // SHUTDOWN: 01:00
        checkAndWarn(server, hour, minute, second, currentTime, 1, 0, "SHUTDOWN", lastShutdownWarnings, "lastShutdownWarning30Sec");
    }

    private void checkAndWarn(MinecraftServer server, int hour, int minute, int second, 
                               long currentTime, int targetHour, int targetMinute, String type, 
                               long[] warnings, String warn30SecKey) {
        // Sjekk minuttvarslinger
        for (int i = 0; i < WARNING_TIMES.length; i++) {
            int warningMinute = WARNING_TIMES[i];
            if (hour == targetHour && minute == targetMinute - warningMinute && second == 0) {
                if (currentTime - warnings[i] > 60000) { // Sjekk at det ikke er sendt i løpet av forrige minutt
                    broadcastMessage(server, "🔴 " + type + " om " + warningMinute + " minutter!");
                    warnings[i] = currentTime;
                }
            }
        }

        // Sjekk 30 sekunders varsel
        if (hour == targetHour && minute == targetMinute - 1 && second == 30) {
            if (warn30SecKey.equals("lastRestartWarning30Sec")) {
                if (currentTime - lastRestartWarning30Sec > 60000) {
                    broadcastMessage(server, "🔴 " + type + " om 30 sekunder!");
                    lastRestartWarning30Sec = currentTime;
                }
            } else {
                if (currentTime - lastShutdownWarning30Sec > 60000) {
                    broadcastMessage(server, "🔴 " + type + " om 30 sekunder!");
                    lastShutdownWarning30Sec = currentTime;
                }
            }
        }
    }

    private void broadcastMessage(MinecraftServer server, String message) {
        if (server != null && server.getPlayerManager() != null) {
            server.getPlayerManager().broadcast(Text.of(message), false);
        }
    }
}
