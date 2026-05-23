# Restart Advarsel - Fabric Mod

En Fabric mod for Minecraft 1.21.4 som varsler alle spillere i chat før restart og shutdown.

## Funksjoner

- 🔴 Varsler 30 minutter før restart (16:00 Oslo-tid)
- 🔴 Varsler 15 minutter før restart
- 🔴 Varsler 10 minutter før restart
- 🔴 Varsler 5 minutter før restart
- 🔴 Varsler 2 minutter før restart
- 🔴 Varsler 30 sekunder før restart

- 🔴 Varsler 30 minutter før shutdown (01:00 Oslo-tid)
- 🔴 Varsler 15 minutter før shutdown
- 🔴 Varsler 10 minutter før shutdown
- 🔴 Varsler 5 minutter før shutdown
- 🔴 Varsler 2 minutter før shutdown
- 🔴 Varsler 30 sekunder før shutdown

## Installasjon

1. **Installer Fabric Loader**: https://fabricmc.net/use/installer/
   - Velg Minecraft 1.21.4

2. **Last ned moden**:
   - Bygger prosjektet: `./gradlew build`
   - JAR-filen ligger i `build/libs/restartadvarsel-1.0.0.jar`

3. **Plasser moden**:
   - Kopier JAR-filen til `.minecraft/mods` mappen

4. **Start Minecraft** med Fabric-profilen

## Bygging fra kilde

```bash
# Klone repositoryet
git clone https://github.com/Pat-Dev1/RestartAdvarsel.git
cd RestartAdvarsel

# Bygg moden
./gradlew build

# Moden ligger nå i build/libs/
```

## Tidssone

Moden bruker **Oslo-tidssone (Europe/Oslo)** som er UTC+1 (eller UTC+2 sommertid).

## Lisens

MIT
