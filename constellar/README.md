# Constellar (nome provisório)

Mod para Minecraft 1.21.1 / NeoForge 21.1.x — fase 1.

## Como gerar o .jar
1. Instale o JDK 21.
2. Dentro desta pasta, rode:
   - com Gradle instalado:  `gradle wrapper --gradle-version 8.11.1` e depois `./gradlew build`
   - ou abra a pasta no IntelliJ IDEA (ele configura o Gradle sozinho) e rode a tarefa `build`.
3. O arquivo ficará em `build/libs/constellar-0.1.0.jar`. Coloque na pasta `mods` do NeoForge 1.21.1.

Para testar direto no ambiente de dev: `gradle runClient`.

## O que tem
- Capability própria de energia: Forcefield (FD)
- Starlight Generator: gera FD à noite com céu aberto (bônus conforme a fase da lua)
- Forcefield Battery: armazena FD
- Forcefield Reader: mostra o FD de um bloco (clique direito)
- Telescope: descobre constelações à noite (salvo no jogador)
- Constelações por datapack: `data/constellar/constellar/constellation/*.json`

## Teste rápido (criativo)
1. Gerador + bateria encostados, em um lugar com céu aberto.
2. `/time set night` e use o Leitor na bateria.
3. Use o Telescópio à noite, ao ar livre.
