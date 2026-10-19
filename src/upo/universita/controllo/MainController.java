package upo.universita.controllo;

import upo.universita.eccezioni.CercaAuleLibereException;
import upo.universita.eccezioni.PrenotaAulaException;
import upo.universita.eccezioni.StampaReportException;
import upo.universita.modello.*;
import upo.universita.vista.Frame;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import upo.universita.eccezioni.InserimentoAulaException;
import upo.universita.eccezioni.InserimentoDocenteException;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class MainController {
    private static boolean continua = true;
    private Frame frame;

    private GestoreSistema gestoreSistema = new GestoreSistema();
    
    /***
     * Costruttore della classe Frame
     * 
     * @param frame Il frame dell'applicazione
     */
    public MainController(Frame frame) {
        this.frame = frame;
    }
    
    /***
     * Verifica se l'esecuzione del programma deve continuare
     * 
     * @return	true se l'esecuzione deve continuare, false altrimenti
     */
    public boolean isContinua() {
        return continua;
    }
    
    /***
     * Apre una finestra di dialogo per inserire una nuova aula nel sistema.
     * 
     * @throws InserimentoAulaException Lanciata se si verificano errori durante l'inserimento dell'aula.
     */
    public void inserisciAula() throws InserimentoAulaException {
    
    	JTextField nomeAulaTextField = new JTextField();
    	JTextField capienzaAulaTextField = new JTextField();

    	Aula.Types[] opzioni = Aula.Types.values();
    	JComboBox<Aula.Types> comboBox = new JComboBox<>(opzioni);
    	
    	Object[] message = {
    		"Nome aula: ", nomeAulaTextField,
    		"Capienza aula: ", capienzaAulaTextField,
            "Tipo aula: ", comboBox,
    	};
    	
        int option = JOptionPane.showConfirmDialog(null, message, "Inserire l'aula", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String nomeAula = nomeAulaTextField.getText();
            String capienzaAula = capienzaAulaTextField.getText();
            
            if (nomeAula.isEmpty() || capienzaAula.isEmpty()) {
                throw new InserimentoAulaException("Compilare tutti i campi prima di proseguire.");
            }
            
            int capienza;
            try {
                capienza = Integer.parseInt(capienzaAula);
            } catch (NumberFormatException e) {
                throw new InserimentoAulaException("Capienza inserita non valida.");
            }
            
            Aula.Types tipo = (Aula.Types) comboBox.getSelectedItem();
            gestoreSistema.addAula(nomeAula, capienza, tipo);
            
            JOptionPane.showMessageDialog(null, "Aula aggiunta con successo!", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
        }
    }
	
    /***
     * Mostra l'elenco delle aule disponibili nell'interfaccia grafica.
     * 
     */
    public void showAuleDisponibili() {
        StringBuilder auleDisponibili = new StringBuilder();
        auleDisponibili.append("Aule disponibili:\n");

        List<Aula> aule = gestoreSistema.getAule();
        
        for (Aula aula : aule) {
            auleDisponibili.append(aula.toString());
        }
        
        SwingUtilities.invokeLater(() -> {
            frame.updateAuleDisponibili(auleDisponibili.toString());
        });
    }
    
    
    /***
     * Consente l'inserimento di un nuovo docente attraverso l'interfaccia grafica.
     * L'utente deve fornire il nome, il cognome e la matricola del docente tramite una finestra di dialogo.
     *
     * @throws InserimentoDocenteException se ci sono errori nell'inserimento dei dati o l'operazione di inserimento fallisce.
     */
    public void inserisciDocente() throws InserimentoDocenteException {
            	
    	JTextField nomeDocenteTextField = new JTextField();
    	JTextField cognomeDocenteTextField = new JTextField();
    	JTextField matricolaDocenteTextField = new JTextField();
    	
    	Object[] message = {
    		"Nome docente: ", nomeDocenteTextField,
    		"Cognome docente:", cognomeDocenteTextField,
    		"Matricola docente: ", matricolaDocenteTextField,
    	};
    	
    	int option = JOptionPane.showConfirmDialog(null, message, "Inserire il docente", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String nomeDocente = nomeDocenteTextField.getText();
            String cognomeDocente = cognomeDocenteTextField.getText();
            String matricolaDocente = matricolaDocenteTextField.getText();
            
            if (nomeDocente.isEmpty() ||  cognomeDocente.isEmpty() || matricolaDocente.isEmpty()) {
                throw new InserimentoDocenteException("Compilare tutti i campi prima di proseguire.");
            }
            
            gestoreSistema.addDocente(nomeDocente, cognomeDocente, matricolaDocente);
            
            JOptionPane.showMessageDialog(null, "Docente aggiunto con successo!", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /***
     * Mostra i docenti liberi nell'interfaccia grafica
     * 
     */
    public void showDocentiDisponibili() {
        StringBuilder docentiDisponibili = new StringBuilder();
        docentiDisponibili.append("Docenti disponibili:\n");

        List<Docente> docenti = gestoreSistema.getDocenti();

        for (Docente docente : docenti) {
            docentiDisponibili.append(docente.toString());
        }

        SwingUtilities.invokeLater(() -> {
            frame.updateDocentiDisponibili(docentiDisponibili.toString());
        });
    }
    
    /***
     * Consente la prenotazione di un'aula attraverso un'interfaccia grafica basata su finestre di dialogo.
     * L'utente deve fornire i dettagli della prenotazione, tra cui il nome dell'aula, la data, l'orario di inizio e fine,
     * e la matricola del docente. Se l'utente preme il pulsante "OK" e i dati inseriti sono validi, viene effettuata la prenotazione.
     *
     * @throws PrenotaAulaException se si verifica un errore durante la prenotazione o se i dati inseriti non sono validi.
     * @throws IOException se si verifica un errore di I/O.
     */
    public void prenotaAula() throws PrenotaAulaException, IOException {

        JTextField nomeAulaTextField = new JTextField();
        JTextField giornoTextField = new JTextField();
        JTextField oraInizioTextField = new JTextField();
        JTextField oraFineTextField = new JTextField();
        JTextField matricolaDocenteTextField = new JTextField();

        Object[] message = {
            "Aula da prenotare:", nomeAulaTextField,
            "Giorno (dd/MM/yyyy):", giornoTextField,
            "Ora di inizio (HH:mm):", oraInizioTextField,
            "Ora di fine (HH:mm):", oraFineTextField,
            "Matricola del docente:", matricolaDocenteTextField,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Effettua prenotazione", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
                       
            String nomeAula = nomeAulaTextField.getText();
            String giornoInput = giornoTextField.getText();
            String oraInizioInput = oraInizioTextField.getText();
            String oraFineInput = oraFineTextField.getText();
            String matricolaDocente = matricolaDocenteTextField.getText();


            Aula aulaPrenotata = gestoreSistema.getAulaByNome(nomeAula);
            if (aulaPrenotata == null) {
                throw new PrenotaAulaException("L'aula specificata non esiste.");
            }

            LocalDate dataPrenotazione = Utility.parseLocalDate(giornoInput);
            if (dataPrenotazione == null) {
                throw new PrenotaAulaException("Data di prenotazione non valida.");
            }

            LocalTime oraInizioPrenotazione = Utility.parseLocalTime(oraInizioInput);
            LocalTime oraFinePrenotazione = Utility.parseLocalTime(oraFineInput);

            if (oraInizioPrenotazione == null || oraFinePrenotazione == null) {
                throw new PrenotaAulaException("Orario di prenotazione non valido.");
            }

            if (oraInizioPrenotazione.isAfter(oraFinePrenotazione)) {
                throw new PrenotaAulaException("L'ora di inizio deve essere precedente all'ora di fine.");
            } else if (oraInizioPrenotazione.equals(oraFinePrenotazione)) {
                throw new PrenotaAulaException("L'ora di inizio e l'ora di fine non possono essere uguali.");
            }

            Docente docentePrenotazione = gestoreSistema.getDocenteByMatricola(matricolaDocente);
            if (docentePrenotazione == null) {
                throw new PrenotaAulaException("Il docente specificato non esiste.");
            }

            if (aulaPrenotata.isPrenotata(dataPrenotazione, oraInizioPrenotazione, oraFinePrenotazione)) {
                 throw new PrenotaAulaException("L'aula é già prenotata in questo orario.");
            }

            gestoreSistema.prenotaAula(aulaPrenotata.getNome(), dataPrenotazione, oraInizioPrenotazione, oraFinePrenotazione, docentePrenotazione.getMatricola());

            JOptionPane.showMessageDialog(null, "Prenotazione effettuata con successo!", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /***
     * Mostra le aule prenotate nell'interfaccia grafica
     */
    public void showAulePrenotateConSuccesso() {
    	StringBuilder aulePrenotateConSuccesso = new StringBuilder();
    	aulePrenotateConSuccesso.append("Aule prenotate con successo:\n");
    	
        List<Prenotazione> prenotazioniEffettuate = gestoreSistema.getPrenotazioni();

        for (Prenotazione prenotazione : prenotazioniEffettuate) {
        	aulePrenotateConSuccesso.append(prenotazione.toString());
        }
        
        SwingUtilities.invokeLater(() -> {
            frame.updateAulePrenotateConSuccesso(aulePrenotateConSuccesso.toString());
        });
        
    }
    
    /***
     * Effettua la ricerca di aule libere in base ai criteri specificati attraverso un'interfaccia grafica basata su finestre di dialogo.
     * L'utente deve fornire i criteri di ricerca, tra cui data, orario di inizio, orario di fine e capienza minima. 
     * Se l'utente preme il pulsante "OK" e i dati inseriti sono validi, vengono restituite le aule che soddisfano i criteri di ricerca.
     * 
     * @throws CercaAuleLibereException se si verifica un errore durante la ricerca
     */    
    public void cercaAuleLibere() throws CercaAuleLibereException {
        JTextField dataTextField = new JTextField();
        JTextField oraInizioTextField = new JTextField();
        JTextField oraFineTextField = new JTextField();
        JTextField capienzaMinimaTextField = new JTextField();

        Object[] message = {
            "Data (dd/MM/yyyy):", dataTextField,
            "Ora di inizio (HH:mm):", oraInizioTextField,
            "Ora di fine (HH:mm):", oraFineTextField,
            "Capienza minima:", capienzaMinimaTextField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cerca Aule Libere", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {

            String dataInput = dataTextField.getText();
            LocalDate dataRicerca = Utility.parseLocalDate(dataInput);

            if (!Utility.isValidDateFormat(dataRicerca, "dd/MM/yyyy", dataInput)) {
                throw new CercaAuleLibereException("Data inserita non valida.");
            }

            String oraInizioInput = oraInizioTextField.getText();
            LocalTime oraInizioRicerca = Utility.parseLocalTime(oraInizioInput);

            if (!Utility.isValidTimeFormat(oraInizioRicerca, "HH:mm", oraInizioInput)) {
                throw new CercaAuleLibereException("Ora di inizio inserita non valida.");
            }

            String oraFineInput = oraFineTextField.getText();
            LocalTime oraFineRicerca = Utility.parseLocalTime(oraFineInput);

            if (!Utility.isValidTimeFormat(oraFineRicerca, "HH:mm", oraFineInput)) {
                throw new CercaAuleLibereException("Ora di fine inserita non valida.");
            }

            String capienzaMinimaInput = capienzaMinimaTextField.getText();
            int capienzaMinimaRicerca;
            try {
                capienzaMinimaRicerca = Integer.parseInt(capienzaMinimaInput);
            } catch (NumberFormatException e) {
                throw new CercaAuleLibereException("Capienza minima inserita non valida.");
            }

            StringBuilder risultatiRicerca = new StringBuilder();
            risultatiRicerca.append("Risultati della ricerca:\n");

            List<Aula> auleLibere = gestoreSistema.getAuleLibere(dataRicerca, oraInizioRicerca, oraFineRicerca, capienzaMinimaRicerca);

            if (auleLibere.size() == 0) {
                throw new CercaAuleLibereException("Nessuna aula disponibile corrisponde ai criteri di ricerca.");
            }

            for (Aula aula: auleLibere) {
                risultatiRicerca.append(aula.toString());
            }

            JOptionPane.showMessageDialog(null, risultatiRicerca.toString(), "Risultati della ricerca", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    
    /***
     * Mostra una finestra di dialogo per selezionare il tipo di report da stampare: report per aula o report per docente.
     * Per il report per aula, l'utente deve inserire il nome dell'aula e la data di riferimento, quindi il sistema genera e mostra il report corrispondente.
     * Per il report per docente, l'utente deve inserire la matricola del docente e la data di riferimento, quindi il sistema genera e mostra il report corrispondente.
     * Se l'utente preme il pulsante "OK" e i dati inseriti sono validi, il report viene visualizzato in una finestra di dialogo.
     *
     * @throws StampaReportException se si verifica un errore durante la generazione del report
     */
    public void stampaReport() throws StampaReportException {

        JTextField sceltaTextField = new JTextField();
        Object[] message = {
            "Seleziona il tipo di report:",
            "1. Report per aula",
            "2. Report per docente",
            "Tipo di report (1 o 2):", sceltaTextField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Stampa Report", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String sceltaInput = sceltaTextField.getText();

            int scelta;
            try {
                scelta = Integer.parseInt(sceltaInput);
            } catch (NumberFormatException e) {
                throw new StampaReportException("Scelta non valida. Riprova.");
            }

            if (scelta == 1) {
                JTextField nomeAulaTextField = new JTextField();
                JTextField dataTextField = new JTextField();
                Object[] aulaMessage = {
                    "Inserisci il nome dell'aula:", nomeAulaTextField,
                    "Inserisci la data (formato: dd/MM/yyyy):", dataTextField
                };

                int aulaOption = JOptionPane.showConfirmDialog(null, aulaMessage, "Report per Aula", JOptionPane.OK_CANCEL_OPTION);

                if (aulaOption == JOptionPane.OK_OPTION) {
                    String nomeAula = nomeAulaTextField.getText().toLowerCase();
                    String dataString = dataTextField.getText();
                    LocalDate data = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    
                    
                    if (!Utility.isValidDateFormat(dataString, "dd/MM/yyyy")) {
                        throw new StampaReportException("Data inserita non valida.");
                    }
                    
                    Aula aula = gestoreSistema.getAulaByNome(nomeAula);
                    if (aula == null) {
                        throw new StampaReportException("L'aula inserita non esiste.");
                    }
                    
                    List<Prenotazione> prenotazioniAula = gestoreSistema.getPrenotazioniByData(aula, data);
                    
                    if (prenotazioniAula.isEmpty()) {
                        throw new StampaReportException("Non ci sono report per quest'aula.");
                    }
                    
                    ReportAula report = new ReportAula(aula, data, prenotazioniAula);
                    String stringReport = report.generaReport();

                    StringBuilder reportAula = new StringBuilder();
                    reportAula.append(stringReport);

                    JOptionPane.showMessageDialog(null, reportAula.toString(), "Report per Aula", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (scelta == 2) {
                JTextField matricolaDocenteTextField = new JTextField();
                JTextField dataTextField = new JTextField();

                Object[] docenteMessage = {
                    "Inserisci la matricola del docente:", matricolaDocenteTextField,
                    "Inserisci la data (formato: dd/MM/yyyy):", dataTextField
                };

                int docenteOption = JOptionPane.showConfirmDialog(null, docenteMessage, "Report per Docente", JOptionPane.OK_CANCEL_OPTION);
                
                if (docenteOption == JOptionPane.OK_OPTION) {
                    String matricolaDocente = matricolaDocenteTextField.getText();
                    String dataString = dataTextField.getText();

                    Docente docente = gestoreSistema.getDocenteByMatricola(matricolaDocente);
                    if(docente == null) {
                    	throw new StampaReportException("Il docente inserito non esiste.");
                    }
                    
                    if (!Utility.isValidDateFormat(dataString, "dd/MM/yyyy")) {
                        throw new StampaReportException("Data inserita non valida.");
                    }

                    LocalDate data = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    
                    List<Prenotazione> prenotazioniDocente = gestoreSistema.getPrenotazioniByData(docente, data);
                    if(prenotazioniDocente.isEmpty()) {
                    	throw new StampaReportException("Non ci sono report per questo docente.");
                    }
                    
                    ReportDocente report = new ReportDocente(docente, data, prenotazioniDocente);
                    String stringReport = report.generaReport();
                    
                    StringBuilder reportDocente = new StringBuilder();
                    reportDocente.append(stringReport);

                    JOptionPane.showMessageDialog(null, reportDocente.toString(), "Report per Docente", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                throw new StampaReportException("Scelta non valida. Riprova.");
            }
        }
    }
}