package upo.universita.modello;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class Aula {
	
	/***
	 * 
	 * Enumerazione delle dotazioni disponibili per ogni aula
	 *
	 */
    public enum Dotazioni {
        LAVAGNA, PROIETTORE, MICROSCOPIO, COMPUTER
    }
    
    /***
     * 
     * Enumerazione dei tipi di aula disponibili
     *
     */
    public enum Types {
        STANDARD, INFORMATICA, BIOLOGIA
    }

    private String nome;
    private Types tipo;
    private int capienza;
    private Set<Dotazioni> dotazioni;
    private List<Prenotazione> prenotazioni;
    
    
    /***
     * Costruttore della classe Aula
     * 
     * @param nome 		Il nome dell'aula
     * @param capienza  La capienza massima dell'aula
     * @param dotazioni	Set di dotazioni disponibli per aula
     */
    public Aula(String nome, int capienza, Types tipo) {
        this.nome = nome;
        this.capienza = capienza;
        this.dotazioni = new HashSet<>();
        this.prenotazioni = new ArrayList<>();
        
        this.dotazioni.add(Dotazioni.LAVAGNA);
        this.dotazioni.add(Dotazioni.PROIETTORE);
        
        switch(tipo) {
        	case INFORMATICA:
        		this.dotazioni.add(Dotazioni.COMPUTER);
        		break;
        	case BIOLOGIA:
        		this.dotazioni.add(Dotazioni.MICROSCOPIO);
        		break;
        	default:
        		break;
        }
    }


	/***
     * Restituisce il nome dell'aula
     * 
     * @return nome dell'aula specificata
     */
    public String getNome() {
        return nome;
    }
    
    /***
     * Restituisce la capienza dell'aula
     * 
     * @return capienza dell'aula specificata
     */
    public int getCapienza() {
        return capienza;
    }
    
    /***
     * Restituisce un Set di dotazioni disponibili per un'aula
     * 
     * @return Set di dotazioni
     */
    public Set<Dotazioni> getDotazioni(){
    	return dotazioni;
    }
    
    /***
     * Restituisce il tipo di aula
     * 
     * @return tipo dell'aula specificata
     */
    public Types getTipo() {
        return tipo;
    }
    
    /***
     * Restituisce la lista delle prenotazioni effettuate per aula
     * 
     * @return lista prenotazioni effettuate
     */
    public List<Prenotazione> getPrenotazioni(){
    	return prenotazioni;
    }
    
    /***
     * Verifica se l'aula è gia prenotata in un determinato orario
     * 
     * @param giorno		giorno della prenotazione
     * @param oraInizio   	l'ora di inizio della prenotazione
     * @param oraFine     	l'ora di fine della prenotazione
     * @return	true se l'aula è gia prenotata in questa fascia di orario, altrimenti false
     */
    public boolean isPrenotata(LocalDate giorno, LocalTime oraInizio, LocalTime oraFine) {
        for (Prenotazione prenotazione : prenotazioni) {
            if (prenotazione.getGiorno().equals(giorno)) {
                LocalTime inizioPrenotazione = prenotazione.getOraInizio();
                LocalTime finePrenotazione = prenotazione.getOraFine();

                if ((oraInizio.isAfter(inizioPrenotazione) || oraInizio.equals(inizioPrenotazione)) && oraInizio.isBefore(finePrenotazione)) {
                    return true;
                }

                if ((oraFine.isBefore(finePrenotazione) || oraFine.equals(finePrenotazione)) && oraFine.isAfter(inizioPrenotazione)) {
                    return true;
                }
                
                if (oraInizio.isBefore(inizioPrenotazione) && oraFine.isAfter(finePrenotazione)) {
                    return true;                
                }
            }
        }
        return false;
    }

    /***
     * Ottiene una lista di prenotazioni per l'aula basata su una data specificata
     * 
     * @param data 			La data per la quale si desiderano le prenotazioni
     * @return prenotazioni	Una lista di prenotazioni relativa alla data specificata
     */
    public List<Prenotazione> getPrenotazioniByData(LocalDate data) {
        List<Prenotazione> prenotazioniByData = new ArrayList<>();
        
        for (Prenotazione prenotazione : prenotazioni) {
            if (prenotazione.getGiorno().equals(data)) {
                prenotazioniByData.add(prenotazione);
            }
        }
        return prenotazioniByData;
    }

    /***
     * Aggiunge una prenotazione all'elenco delle prenotazioni dell'aula
     * 
     * @param prenotazione La prenotazione da aggiungere
     */   
    public void aggiungiPrenotazione(Prenotazione prenotazione) {
        prenotazioni.add(prenotazione);
    }
  
    
    /***
     * Restituisce una stringa che rappresenta l'oggetto Aule con i suoi parametri
     * 
     * @return stringa dell'oggetto
     */
    @Override
    public String toString() {
        return "Nome='" + nome + '\'' + ", capienza=" + capienza + ", dotazioni=" + dotazioni + "\n";
    } 
   
}