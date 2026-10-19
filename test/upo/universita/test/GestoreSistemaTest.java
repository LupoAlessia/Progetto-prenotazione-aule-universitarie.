package upo.universita.test;

import org.junit.Before;
import org.junit.Test;
import upo.universita.eccezioni.InserimentoAulaException;
import upo.universita.eccezioni.InserimentoDocenteException;
import upo.universita.eccezioni.PrenotaAulaException;
import upo.universita.modello.Aula;
import upo.universita.modello.Docente;
import upo.universita.modello.GestoreSistema;
import upo.universita.modello.Prenotazione;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

public class GestoreSistemaTest {

    private GestoreSistema gestoreSistema;
    
    /***
     * Inizializza il GestoreSistema prima dell'esecuzione di ogni test
     */
    @Before
    public void setUp() {
        gestoreSistema = new GestoreSistema();
    }
    
    /***
     * Verifica che il metodo getAule() restituisca una lista di aule non nulla e con almeno un'aula presente.
     */
    @Test
    public void testGetAule() {
        List<Aula> aule = gestoreSistema.getAule();
        assertNotNull(aule);
        assertTrue(aule.size() > 0);
    }
    
    /***
     * Verifica che il metodo getDocenti() restituisca una lista di docenti non nulla e con almeno un docente presente.
     */
    @Test
    public void testGetDocenti() {
        List<Docente> docenti = gestoreSistema.getDocenti();
        assertNotNull(docenti);
        assertTrue(docenti.size() > 0);
    }
    
    /***
     * Verifica che il metodo getPrenotazioni() restituisca una lista di prenotazioni non nulla e vuota.
     */
    @Test
    public void testGetPrenotazioni() {
        List<Prenotazione> prenotazioni = gestoreSistema.getPrenotazioni();
        assertNotNull(prenotazioni);
        assertTrue(prenotazioni.isEmpty());
    }
    
    /***
     * Verifica che il metodo addDocente() aggiunga correttamente un docente al sistema.
     */
    @Test
    public void testAddDocente() {
        int docentiIniziali = gestoreSistema.getDocenti().size();
        try {
            gestoreSistema.addDocente("Nome", "Cognome", "12345");
        } catch (InserimentoDocenteException e) {
            fail("Aggiunta del docente non riuscita: " + e.getMessage());
        }
        int docentiDopoAggiunta = gestoreSistema.getDocenti().size();
        assertEquals(docentiIniziali + 1, docentiDopoAggiunta);
    }
    
    /***
     * Verifica che il metodo addAula() aggiunga correttamente un'aula al sistema.
     */
    @Test
    public void testAddAula() {
        int auleIniziali = gestoreSistema.getAule().size();
        try {
            gestoreSistema.addAula("Nuova Aula", 100, Aula.Types.STANDARD);
        } catch (InserimentoAulaException e) {
            fail("Aggiunta dell'aula non riuscita: " + e.getMessage());
        }
        int auleDopoAggiunta = gestoreSistema.getAule().size();
        assertEquals(auleIniziali + 1, auleDopoAggiunta);
    }
    
    /***
     * Verifica che il metodo prenotaAula() prenoti correttamente un'aula.
     */
    @Test   
    public void testPrenotaAula() {
        List<Aula> auleLibere = gestoreSistema.getAuleLibere(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), 30);
        if (!auleLibere.isEmpty()) {
            try {
                gestoreSistema.prenotaAula(auleLibere.get(0).getNome(), LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), "1001");
                assertTrue(true); // La prenotazione è riuscita
            } catch (PrenotaAulaException e) {
                fail("Prenotazione fallita: " + e.getMessage());
            }
        } else {
            fail("Nessuna aula libera disponibile per la prenotazione.");
        }
    }
    
    /***
     * Verifica che il metodo getPrenotazioniByDataAula() restituisca correttamente le prenotazioni per una data e un'aula specifica.
     */
    @Test
    public void testGetPrenotazioniByDataAula() {
        List<Aula> auleLibere = gestoreSistema.getAuleLibere(LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(16, 0), 30);
        if (!auleLibere.isEmpty()) {
            try {
                gestoreSistema.prenotaAula(auleLibere.get(0).getNome(), LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(16, 0), "1001");
                List<Prenotazione> prenotazioni = gestoreSistema.getPrenotazioniByData(auleLibere.get(0), LocalDate.now());
                assertFalse(prenotazioni.isEmpty());
            } catch (PrenotaAulaException e) {
                fail("Prenotazione fallita: " + e.getMessage());
            }
        } else {
            fail("Nessuna aula libera disponibile per la prenotazione.");
        }	
    }
    
    /***
     * Verifica che il metodo getPrenotazioniByDataDocente() restituisca correttamente le prenotazioni per una data e un docente specifici.
     */
    @Test
    public void testGetPrenotazioniByDataDocente() {
        List<Docente> docenti = gestoreSistema.getDocenti();
        if (!docenti.isEmpty()) {
            Docente docente = docenti.get(0);
            List<Prenotazione> prenotazioni = gestoreSistema.getPrenotazioniByData(docente, LocalDate.now());
            assertNotNull(prenotazioni);
        } else {
            fail("Nessun docente disponibile per il test.");
        }   	
    }
    
    /***
     * Verifica che il metodo getAuleLibere() restituisca correttamente una lista di aule libere per una data e un intervallo di orari specifici.
     */
    @Test
    public void testGetAuleLibere() {
        List<Aula> auleLibere = gestoreSistema.getAuleLibere(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), 30);
        assertNotNull(auleLibere);
    }
    
    /***
     * Verifica che il metodo getAulePrenotate() restituisca correttamente una lista di aule prenotate.
     */
    @Test
    public void testGetAulePrenotate() {
        List<Aula> aulePrenotate = gestoreSistema.getAulePrenotate();
        assertNotNull(aulePrenotate);
    }
    
    /***
     * Verifica che il metodo getAulaByNome() restituisca correttamente un'aula per nome.
     */
    @Test
    public void testGetAulaByNome() {
        String nomeAula = "Aula 101";
        Aula aula = gestoreSistema.getAulaByNome(nomeAula);
        assertNotNull(aula);
    }
    
    /***
     * Verifica che il metodo getDocenteByMatricola() restituisca correttamente un docente per matricola.
     */
    @Test
    public void testGetDocenteByMatricola() {
        String matricolaDocente = "1001";
        Docente docente = gestoreSistema.getDocenteByMatricola(matricolaDocente);
        assertNotNull(docente);
    }
}