package upo.universita.modello;

import java.time.LocalDate;
import java.time.LocalTime;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class Prenotazione implements Comparable<Prenotazione> {
    private LocalDate giorno;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private String matricolaDocente;
    private String nomeAula;
    
    /***
     * Costruttore della classe Prenotazione
     * 
     * @param giorno			Giorno della prenotazione
     * @param oraInizio			Orario di inizio della prenotazione
     * @param oraFine			Orario di fine della prenotazione
     * @param matricolaDocente	Matricola del docente che ha effettuato la prenotazione
     * @param nomeAula			Nome dell'aula prenotata
     */
    public Prenotazione(LocalDate giorno, LocalTime oraInizio, LocalTime oraFine, String matricolaDocente, String nomeAula) {
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.matricolaDocente = matricolaDocente;
        this.nomeAula = nomeAula;
    }
    
    /***
     * Restituisce il giorno di una prenotazione
     * 
     * @return giorno della prenotazione
     */
	public LocalDate getGiorno() {
        return giorno;
    }
	
	/***
	 * Restituisce l'ora di inizio di una prenotazione
	 * 
	 * @return Orario di inizio della prenotazione 
	 */
    public LocalTime getOraInizio() {
        return oraInizio;
    }
    
    /***
     * Restituisce l'ora di fine di una prenotazione
     * 
     * @return Orario di fine della prenotazione
     */
    public LocalTime getOraFine() {
        return oraFine;
    }
    
    /***
     * Restituisce la matricola del docente che ha effettuato la prenotazione
     * 
     * @return Matricola del docente
     */
    public String getMatricolaDocente() {
        return matricolaDocente;
    }
    
    /***
     * Restituisce il nome dell'aula prenotata
     * 
     * @return Nome dell'aula prenotata
     */
    public String getNomeAula() {
        return nomeAula;
    }
    
    /***
     * Restituisce una rappresentazione testuale della prenotazione
     * 
     * @return Una stringa contenente la prenotazione
     */   
    @Override
    public String toString() {
        return "Aula: " + nomeAula + "\nGiorno: " + giorno + "\nOra inizio: " + oraInizio + "\nOra fine: " + oraFine + "\nDocente: " + matricolaDocente + "\n---------------------\n";
    }
    
    /***
     * Consente il confronto tra prenotazioni in base a data e orario
     * 
     * @param t Prenotazione da confrontare
     * @return Un valore negativo se questa prenotazione è precedente a t, 0 se sono uguali, un valore positivo se questa prenotazione è successiva a t
     */
    @Override
    public int compareTo(Prenotazione t) {
        if (this.getGiorno().isBefore(t.getGiorno()) || (this.getGiorno().isEqual(t.getGiorno()) && this.getOraInizio().isBefore(t.getOraInizio()))) {
            return -1;
        } else if (this.getGiorno().isEqual(t.getGiorno()) && this.getOraInizio().equals(t.getOraInizio())) {
            return 0;
        } else return 1;
    }
}