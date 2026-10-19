<img width="32" height="32" alt="upo" src="https://github.com/user-attachments/assets/d2e0de2f-7869-4572-8ccb-ab8aa03e1b39" />

# Progetto-prenotazione-aule-universitarie.
Applicazione desktop in Java (Swing) basata sul pattern MVC per la gestione e la prenotazione di aule e docenti universitari. Il sistema previene le sovrapposizioni orarie, filtra le aule libere, genera report ordinati e gestisce eccezioni custom. Include una suite di unit test e un'architettura orientata agli oggetti ben strutturata.


# 🏫 Sistema di Gestione e Prenotazione Aule Universitarie

Progetto sviluppato per l'esame di **Paradigmi di Programmazione** (Traccia 1).  
L'applicazione è un software gestionale desktop sviluppato in **Java** con interfaccia grafica **Swing**, basato sull'architettura **Model-View-Controller (MVC)** e sui principi della programmazione orientata agli oggetti (OOP).


---

## 🚀 Funzionalità Principali
- **Gestione Aule & Docenti:** Inserimento e profilazione di aule (standard, informatica, biologia) con relative dotazioni e anagrafica docenti.
- **Sistema di Prenotazione:** Prenotazione aule con controllo automatico delle sovrapposizioni orarie.
- **Ricerca Avanzata:** Filtro per aule libere o prenotate in base a criteri specifici, con ricerca case-insensitive per nome o matricola.
- **Generazione Report:** Creazione di report formattati delle prenotazioni ordinati per fascia oraria (per singola aula o per docente).
- **Gestione Robusta degli Errori:** Utilizzo di eccezioni personalizzate per intercettare dati non validi o conflitti di prenotazione.

---

## 🛠️ Architettura del Software (Pattern MVC)

Il progetto segue rigorosamente la separazione delle responsabilità tramite il pattern **Model-View-Controller**:

### 📦 Modello (`Model`)
Rappresenta lo stato e la logica di business (completamente disaccoppiato da interazioni di I/O o console):

* **`Aula`**: Modella l'aula (nome, capienza, tipologia, dotazioni) e gestisce l'elenco delle proprie prenotazioni.
* **`Docente`**: Modella il docente (nome, cognome, matricola) e le sue prenotazioni.
* **`Prenotazione`**: Entità che associa un'aula a un docente in uno specifico giorno e intervallo orario. Implementa `Comparable` per l'ordinamento cronologico.
* **`GestoreSistema`**: Controller logico centrale. Mantiene le liste globali, coordina le operazioni ed effettua ricerche e controlli.
* **`Report` (Interfaccia)**: Definisce il contratto `generaReport()` implementato da:
  * `ReportAula`: Genera il prospetto prenotazioni per un'aula ordinato per orario.
  * `ReportDocente`: Genera il prospetto prenotazioni per uno specifico docente.
* **`Utility`**: Helper per la validazione e conversione del formato di date e orari.
* **Eccezioni Personalizzate:** `InserimentoDocenteException`, `InserimentoAulaException`, `PrenotaAulaException`.

### ⚙️ Controllore (`Controller`)
* **`MainController`**: Gestisce il flusso tra vista e modello. Cattura gli eventi utente, esegue le operazioni sul sistema e gestisce le eccezioni del flusso (`CercaAuleLibereException`, `StampaReportException`, `PrenotaAulaException`, ecc.).

### 🖥️ Vista (`View`)
* **`Frame`**: Interfaccia grafica principale (Java Swing) contenente form, pulsanti di azione, tabelle e dialoghi di feedback per l'utente.
* **`MainView`**: Entry-point dell'applicazione contenente il metodo `main()`.

---

## 🧪 Testing Unitario
L'applicazione include una suite di test unitari completa per verificare le funzionalità del modello:

- `AulaTest`: Verifica capienza, tipologia, dotazioni e associazioni.
- `DocenteTest`: Verifica dati anagrafici e aggiunta prenotazioni.
- `GestoreSistemaTest`: Test su ricerche, filtri aule libere e gestione eccezioni.
- `PrenotazioneTest`: Test sull'ordinamento cronologico via `compareTo()`.
- `ReportAulaTest` & `ReportDocenteTest`: Test sulla corretta formattazione e ordinamento dei report generati.

---

## 🔄 Scelte di Design & Refactoring
Durante la fase di sviluppo sono state apportate le seguenti migliorie architetturali:
1. **Accorpamento `GestioneAule`:** Le dotazioni sono state integrate direttamente nella classe `Aula` per eliminare ridondanze.
2. **Introduzione di `GestoreSistema`:** Creata una classe coordinatrice unica per gestire l'intero ecosistema dell'ateneo.
3. **Semplificazione `Prenotazione`:** Eliminata la classe ridondante `PrenotazioniSuccesso`, incorporando l'attributo `nomeAula` direttamente in `Prenotazione`.
4. **Polimorfismo:** Creazione dell'interfaccia `Report` per standardizzare la generazione dell'output.

---

## 🛠️ Tecnologie Utilizzate
- **Linguaggio:** Java
- **Interfaccia Grafica:** Java Swing / AWT
- **Testing:** JUnit
