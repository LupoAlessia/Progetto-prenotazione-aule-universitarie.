package upo.universita.modello;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import upo.universita.eccezioni.InserimentoAulaException;
import upo.universita.eccezioni.InserimentoDocenteException;
import upo.universita.eccezioni.PrenotaAulaException;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class GestoreSistema {
    private List<Docente> listaDocenti = new ArrayList<>();
    private List<Aula> listaAule = new ArrayList<>();
    private List<Prenotazione> listaPrenotazioni = new ArrayList<>();
    
    /***
     * Costruttore della classe GestioneSistema
     * 
     * Inizializza il sistema creando istanze di oggetti Docente e Aula aggiungendoli poi alle specifiche liste
     */
    public GestoreSistema() {

        // Inserimento dei docenti
        Docente docente1 = new Docente("Mario", "Neri", "1001");
        Docente docente2 = new Docente("Luca", "Bianchi", "1002");
        Docente docente3 = new Docente("Lucia", "Verdi", "1003");
        listaDocenti.add(docente1);        
        listaDocenti.add(docente2);     
        listaDocenti.add(docente3);

        // Creazione delle aule
        Aula aula1 = new Aula("Aula 101", 50, Aula.Types.STANDARD);
        Aula aula2 = new Aula("Aula 102", 50, Aula.Types.STANDARD);
        Aula aula3 = new Aula("Laboratorio Informatica", 30, Aula.Types.INFORMATICA);
        Aula aula4 = new Aula("Laboratorio Biologia", 30, Aula.Types.BIOLOGIA);
        listaAule.add(aula1);
        listaAule.add(aula2);
        listaAule.add(aula3);
        listaAule.add(aula4);
    }
    
    /***
     * Ottiene l'elenco delle aule nel sistema
     * 
     * @return	Una lista delle aule presenti nel sistema
     */
    public List<Aula> getAule() {
        return this.listaAule;
    }
    
    /***
     * Ottiene l'elenco dei docenti nel sistema
     * 
     * @return Una lista dei docenti presenti nel sistema
     */
    public List<Docente> getDocenti() {
        return this.listaDocenti;
    }
    
    /***
     * Ottiene l'elenco delle prenotazioni nel sistema
     * 
     * @return Una Lista delle prenotazioni presenti nel sistema
     */
    public List<Prenotazione> getPrenotazioni() {
        return this.listaPrenotazioni;
    }
    
    /***
     * Aggiunge un docente alla lista dei docenti del sistema
     * 
     * @param nome							Il nome del docente da agguingere
     * @param cognome						Il cognome del docente da agguingere
     * @param matricola						La matricola del docente da agguingere
     * @throws InserimentoDocenteException	Viene lanciata un'eccezione se il docente � gi� presente nella lista
     */
    public void addDocente(String nome, String cognome, String matricola) throws InserimentoDocenteException {
        if (getDocenteByMatricola(matricola) != null) {
            throw new InserimentoDocenteException("Il docente � gi� presente nella lista.");
        }
        
        Docente docente = new Docente(nome, cognome, matricola);
        listaDocenti.add(docente);
    }
    
    /***
     * Aggiunge un'aula alla lista delle aule del sistema
     * 
     * @param nome							Il nome del'aula da agguingere
     * @param capienza						La capienza del'aula da agguingere
     * @param tipo							Il tipo del'aula da agguingere
     * @throws InserimentoAulaException		Viene lanciata un'eccezione se l'aula � gi� presente nella lista
     */
    public void addAula(String nome, int capienza, Aula.Types tipo) throws InserimentoAulaException {
        if (getAulaByNome(nome) != null) {
            throw new InserimentoAulaException("L'aula � gia esistente.");
        }
        
        Aula aula = new Aula(nome, capienza, tipo);
        listaAule.add(aula);
    }
    
    /***
     * Effettua la prenotazione di un'aula con un determinato docente, data e orario
     * 
     * @param nomeAula					Il nome dell'aula da prenotare
     * @param giorno					La data in cui effettuare la prenotazione
     * @param oraInizio					L'orario di inizio della prenotazione
     * @param oraFine					L'orario di fine della prenotazione
     * @param matricolaDocente			La matricola del docente che effettua la prenotazione
     * @throws PrenotaAulaException		Viene lanciata un'eccezione se l'aula � gi� prenotata in quell'orario
     */
    public void prenotaAula(String nomeAula, LocalDate giorno, LocalTime oraInizio, LocalTime oraFine, String matricolaDocente) throws PrenotaAulaException {
        for (Aula aula: listaAule) {
            if (aula.getNome().equals(nomeAula)) {
                if (aula.isPrenotata(giorno, oraInizio, oraFine)) {
                    throw new PrenotaAulaException("L'aula � gi� prenotata in questo orario.");
                } else {
                    Prenotazione nuovaPrenotazione = new Prenotazione(giorno, oraInizio, oraFine, matricolaDocente, nomeAula);
                    listaPrenotazioni.add(nuovaPrenotazione);
                    aula.aggiungiPrenotazione(nuovaPrenotazione);
                    
                    Docente docente = getDocenteByMatricola(matricolaDocente);
                    docente.aggiungiPrenotazione(nuovaPrenotazione);
                            
                    return;
                }
            }
        }
    }
    
    /***
     * Ottiene una lista di prenotazioni relative a un'aula specifica per una data specificata
     * 
     * @param aula	L'aula per cui si desiderano le prenotazioni
     * @param data	La data per la quale si desiderano le prenotazioni
     * @return	Una lista di prenotazioni relative all'aula e alla data specificate
     */
    public List<Prenotazione> getPrenotazioniByData(Aula aula, LocalDate data) {
        List<Prenotazione> prenotazioni = aula.getPrenotazioniByData(data);
        return prenotazioni;
    }
    
    /***
     * Ottiene una lista di prenotazioni relative a un docente specifico per una data specificata
     * 
     * @param docente	Il docente per cui si desiderano le prenotazioni
     * @param data	La data per la quale si desiderano le prenotazioni
     * @return	Una lista di prenotazioni relative al docente e alla data specificati
     */
    public List<Prenotazione> getPrenotazioniByData(Docente docente, LocalDate data) {
    	List<Prenotazione> prenotazioni = docente.getPrenotazioniByData(data);
        return prenotazioni;
    }
    
    /***
     * Ottiene una lista di aule libere che soddisfano i criteri specificati.
     * 
     * @param giorno 			La data per la quale si desiderano le aule libere.
     * @param oraInizio 		L'orario di inizio del periodo desiderato.
     * @param oraFine 			L'orario di fine del periodo desiderato.
     * @param capienzaMinima 	La capienza minima richiesta per le aule.
     * @return 	Una lista di aule libere che soddisfano i criteri specificati.
     */
    public List<Aula> getAuleLibere(LocalDate giorno, LocalTime oraInizio, LocalTime oraFine, int capienzaMinima) {
        List<Aula> auleLibere = new ArrayList<>();
        
        for (Aula aula: listaAule) {
            if (aula.getCapienza() >= capienzaMinima && !aula.isPrenotata(giorno, oraInizio, oraFine)) {
                auleLibere.add(aula);
            }
        }
        return auleLibere;
    }
    
    /***
     * Ottiene una lista di aule che sono state prenotate.
     * 
     * @return Una lista di aule che hanno almeno una prenotazione.
     */
    public List<Aula> getAulePrenotate() {
        List<Aula> aulePrenotate = new ArrayList<>();
        for (Aula aula: listaAule) {
            if (aula.getPrenotazioni().size() > 0) {
                aulePrenotate.add(aula);
            }
        }

        return aulePrenotate;
    }
    
    /***
     * Restituisce un'aula in base al suo nome, ignorando maiuscole/minuscole.
     * 
     * @param nomeAula 	Il nome dell'aula da cercare.
     * @return 	L'aula corrispondente al nome specificato, oppure null se non trovata.
     */
    public Aula getAulaByNome(String nomeAula) {
        for (Aula aula : listaAule) {
            if (aula.getNome().equalsIgnoreCase(nomeAula)) {
                return aula;
            }
        }
        return null;
    }
    
    /***
     * Restituisce un docente in base alla sua matricola, ignorando maiuscole/minuscole.
     * 
     * @param matricola 	La matricola del docente da cercare.
     * @return 	Il docente corrispondente alla matricola specificata, oppure null se non trovato.
     */
    public Docente getDocenteByMatricola(String matricola) {
        for (Docente docente : listaDocenti) {
            if (docente.getMatricola().equalsIgnoreCase(matricola)) {
                return docente;
            }
        }
        return null;
    }
}