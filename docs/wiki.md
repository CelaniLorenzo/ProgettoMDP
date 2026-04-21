# Wiki - Il Segreto del Reattore Perduto

##  Funzionalità
- Gioco RPG testuale a scelte
- Sistema di decisioni che influenzano la storia
- Possibili combattimenti
- Finali multipli

## 🏗️ Architettura

Il progetto è strutturato in modo modulare per garantire separazione delle responsabilità e facilità di estensione.

##  Classi principali

### Game
Gestisce il flusso principale del gioco.

### Player
Rappresenta il giocatore (vita, stato, decisioni).

### Enemy
Rappresenta i nemici del gioco.

### StoryManager
Gestisce la logica narrativa e le scelte.

### CombatSystem
Gestisce i combattimenti.

##  Responsabilità

- Ogni classe ha una responsabilità ben definita (SRP)
- Separazione tra logica di gioco e gestione input/output
- Codice progettato per essere estendibile


##  Persistenza dati

Non ancora implementata, ma la struttura permette futura integrazione.

##  Estendibilità

Possibilità di:
- aggiungere nuovi personaggi
- aggiungere nuove scelte
- introdurre nuovi sistemi di gioco


##  Tecnologie

- Java
- Gradle
- Git / GitHub