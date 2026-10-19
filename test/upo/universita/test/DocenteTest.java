package upo.universita.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.universita.modello.Docente;
import upo.universita.modello.Prenotazione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class DocenteTest {

    private Docente docente;
    private LocalDate date;
    private List<Prenotazione> prenotazioni;
    
    /***
     * Inizializza l'istanza docente, la data e la lista delle prenotazioni per ogni test
     */
    @BeforeEach
    void setUp() {
        docente = new Docente("Mario", "Rossi", "123456");
        date = LocalDate.now();
        prenotazioni = new ArrayList<>();
    }
    
    /***
     * Verifica che il metodo getNome() restituisca il nome corretto del docente.
     */
    @Test
    public void testGetNome() {
    	assertEquals("Mario", docente.getNome());
    }
    
    /***
     * Verifica che il metodo getCognome() restituisca il cognome corretto del docente.
     */
    @Test
    public void testGetcognome() {
    	assertEquals("Rossi", docente.getCognome());
    }
    
    /***
     * Verifica che il metodo getMatricola() restituisca la matricola corretta del docente.
     */
    @Test
    public void testGetMatricola() {
    	assertEquals("123456", docente.getMatricola());
    }
    
    /***
     * Verifica che il metodo aggiungiPrenotazione() aggiunga correttamente una prenotazione al docente.
     */
    @Test
    void testAggiungiPrenotazione() {
        Prenotazione prenotazione = new Prenotazione(date, LocalTime.of(14, 0), LocalTime.of(16, 0), "123456", "Aula 1");
        docente.aggiungiPrenotazione(prenotazione);
        List<Prenotazione> prenotazioniDocente = docente.getPrenotazioniByData(date);

        assertEquals(1, prenotazioniDocente.size());
        assertEquals(prenotazione, prenotazioniDocente.get(0));
    }
    
    /***
     * Verifica che il metodo getPrenotazioniByData() restituisca correttamente le prenotazioni per una data specifica.
     */
    @Test
    void testGetPrenotazioniByData() {
        Prenotazione prenotazione1 = new Prenotazione(date, LocalTime.of(14, 0), LocalTime.of(16, 0), "123456", "Aula 1");
        Prenotazione prenotazione2 = new Prenotazione(date, LocalTime.of(10, 0), LocalTime.of(12, 0), "123456", "Aula 2");
        prenotazioni.add(prenotazione1);
        prenotazioni.add(prenotazione2);
        docente.aggiungiPrenotazione(prenotazione1);
        docente.aggiungiPrenotazione(prenotazione2);
        List<Prenotazione> prenotazioniDocente = docente.getPrenotazioniByData(date);

        assertEquals(2, prenotazioniDocente.size());
        assertEquals(prenotazione1, prenotazioniDocente.get(0));
        assertEquals(prenotazione2, prenotazioniDocente.get(1));
    }
    
    /***
     * Verifica che il metodo toString() restituisca una rappresentazione corretta del docente.
     */
    @Test
    public void testToString() { 	
        String expectedToString = "Nome: Mario\nCognome: Rossi\nMatricola: 123456\n---------------------\n";
        assertEquals(expectedToString, docente.toString());
    }
}
