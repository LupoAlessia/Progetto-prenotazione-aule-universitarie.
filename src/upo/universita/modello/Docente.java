package upo.universita.modello;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class Docente {
    private String nome;
    private String cognome;
    private String matricola;
    private List<Prenotazione> prenotazioni;
    
    /***
     * Costruttore della classe Docente
     * 
     * @param nome		Il nome del docente
     * @param cognome	Il cognome del docente
     * @param matricola	La matricola del docente
     */
    public Docente(String nome, String cognome, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.prenotazioni = new ArrayList<>();
    }
    
    /***
     * Restituisce il nome di un docente
     * 
     * @return nome		Una copia del nome del docente specifico
     */
    public String getNome() {
        return new String(nome);
    }
    
    /***
     * Restituisce il cognome di un docente
     * 
     * @return cognome 	Una copia del cognome del docente specifico
     */
    public String getCognome() {
        return new String(cognome);
    }
    
    /***
     * Restituisce la matricola di un docente
     * 
     * @return matricola	Una copia della matricola del docente specifico
     */
    public String getMatricola() {
        return new String(matricola);
    }
    
    /***
     * Aggiunge una prenotazione alla lista delle prenotazioni del docente
     * 
     * @param p La prenotazione da aggiungere
     */
    public void aggiungiPrenotazione(Prenotazione p) {
    	this.prenotazioni.add(p);
    }
    
    /***
     * Restituisce una lista di prenotazioni effettuate per l'aula in una determinata data
     * 
     * @param data 			La data per cui si desidera ottenere le prenotazioni
     * @return prenotazioni Una lista di prenotazioni effettuate per l'aula nella data specificata
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
     * Restituisce una stringa che rappresenta l'oggetto Docente con i suoi parametri
     * 
     * @return stringa dell'oggetto
     */
    @Override
    public String toString() {
        return "Nome: " + nome + "\nCognome: " + cognome + "\nMatricola: " + matricola + "\n---------------------\n";
    }
    
}