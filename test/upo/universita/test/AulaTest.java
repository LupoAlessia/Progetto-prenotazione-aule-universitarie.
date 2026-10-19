package upo.universita.test;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.List;

import upo.universita.modello.Aula;
import upo.universita.modello.Aula.Dotazioni;
import upo.universita.modello.Aula.Types;
import upo.universita.modello.Prenotazione;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class AulaTest {

    private Aula aula;
    
    /***
     * Inizializza un'istanza di Aula per eseguire i test.
     */
    @Before
    public void setUp() {
        aula = new Aula("Aula1", 50, Types.STANDARD);
    }
    
    /***
     * Verifica che il metodo getNome() restituisca il nome corretto dell'aula.
     */
    @Test
    public void testGetNome() {
        assertEquals("Aula1", aula.getNome());
    }
    
    /***
     * Verifica che il metodo getCapienza() restituisca la capienza corretta dell'aula.
     */
    @Test
    public void testGetCapienza() {
        assertEquals(50, aula.getCapienza());
    }
    
    /***
     * Verifica che il metodo getDotazioni() restituisca un insieme corretto di dotazioni disponibili per l'aula.
     */
    @Test
    public void testGetDotazioni() {
        Set<Dotazioni> dotazioni = aula.getDotazioni();
        assertTrue(dotazioni.contains(Dotazioni.LAVAGNA));
        assertTrue(dotazioni.contains(Dotazioni.PROIETTORE));
        assertFalse(dotazioni.contains(Dotazioni.MICROSCOPIO));
        assertFalse(dotazioni.contains(Dotazioni.COMPUTER));
    }
    
    /***
     * Verifica che il metodo getTipo() restituisca il tipo corretto dell'aula.
     */
    @Test
    public void testGetTipo() {
        assertEquals(Types.STANDARD, aula.getTipo());
    }
    
    /***
     * Verifica che il metodo getPrenotazioni() restituisca una lista non nulla di prenotazioni.
     */
    @Test
    public void testGetPrenotazioni() {       
        assertNotNull(aula.getPrenotazioni());
    }
    
    /***
     * Verifica che il metodo aggiungiPrenotazione() aggiunga correttamente una prenotazione all'aula.
     */
    @Test
    public void testAggiungiPrenotazione() {
        LocalDate oggi = LocalDate.now();
        LocalTime oraInizio = LocalTime.of(10, 0);
        LocalTime oraFine = LocalTime.of(12, 0);
        String matricolaDocente = "12345";
        String nomeAula = "Aula1";

        Prenotazione prenotazione = new Prenotazione(oggi, oraInizio, oraFine, matricolaDocente, nomeAula);
        aula.aggiungiPrenotazione(prenotazione);
        List<Prenotazione> prenotazioni = aula.getPrenotazioni();
        assertTrue(prenotazioni.contains(prenotazione));
    }
    
    /***
     * Verifica che il metodo isPrenotata() restituisca correttamente se l'aula è prenotata in un determinato orario.
     */
    @Test
    public void testIsPrenotata() {
        LocalDate oggi = LocalDate.now();
        LocalTime oraInizio = LocalTime.of(10, 0);
        LocalTime oraFine = LocalTime.of(12, 0);
        String matricolaDocente = "12345";
        String nomeAula = "Aula1";

        Prenotazione prenotazione = new Prenotazione(oggi, oraInizio, oraFine, matricolaDocente, nomeAula);
        aula.aggiungiPrenotazione(prenotazione);

        assertTrue(aula.isPrenotata(oggi, oraInizio, oraFine));
        assertFalse(aula.isPrenotata(oggi, LocalTime.of(9, 0), LocalTime.of(10, 0)));
    }
    
    /***
     * Verifica che il metodo getPrenotazioniByData() restituisca correttamente le prenotazioni per una data specifica.
     */
    @Test
    public void testGetPrenotazioniByData() {
        LocalDate oggi = LocalDate.now();
        LocalDate domani = LocalDate.now().plusDays(1);
        String matricolaDocente = "12345";
        String nomeAula = "Aula1";

        Prenotazione prenotazioneOggi = new Prenotazione(oggi, LocalTime.of(10, 0), LocalTime.of(12, 0), matricolaDocente, nomeAula);
        Prenotazione prenotazioneDomani = new Prenotazione(domani, LocalTime.of(14, 0), LocalTime.of(16, 0), matricolaDocente, nomeAula);

        aula.aggiungiPrenotazione(prenotazioneOggi);
        aula.aggiungiPrenotazione(prenotazioneDomani);

        List<Prenotazione> prenotazioniOggi = aula.getPrenotazioniByData(oggi);
        List<Prenotazione> prenotazioniDomani = aula.getPrenotazioniByData(domani);

        assertTrue(prenotazioniOggi.contains(prenotazioneOggi));
        assertFalse(prenotazioniOggi.contains(prenotazioneDomani));
        assertTrue(prenotazioniDomani.contains(prenotazioneDomani));
        assertFalse(prenotazioniDomani.contains(prenotazioneOggi));
    }
    
    /***
     * Verifica che il metodo toString() restituisca una rappresentazione corretta dell'aula.
     */
    @Test
    public void testToString() {  	
        String expectedToString = "Nome='Aula1', capienza=50, dotazioni=[PROIETTORE, LAVAGNA]\n";
        assertEquals(expectedToString, aula.toString());
    }
}