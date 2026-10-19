package upo.universita.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import upo.universita.modello.Prenotazione;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrenotazioneTest {
	
	/***
	 *  Verifica se il metodo getGiorno() restituisce il giorno corretto
	 */
    @Test
    void testGetGiorno() {
        LocalDate data = LocalDate.of(2023, 9, 4);
        LocalTime oraInizio = LocalTime.of(10, 0);
        LocalTime oraFine = LocalTime.of(12, 0);
        String matricolaDocente = "123456";
        String nomeAula = "Aula 1";
        Prenotazione prenotazione = new Prenotazione(data, oraInizio, oraFine, matricolaDocente, nomeAula);

        assertEquals(data, prenotazione.getGiorno());
    }
    
    /***
     * Verifica se il metodo getOraInizio() restituisce l'orario di inizio corretto
     */
    @Test
    void testGetOraInizio() {
        LocalDate data = LocalDate.of(2023, 9, 4);
        LocalTime oraInizio = LocalTime.of(10, 0);
        LocalTime oraFine = LocalTime.of(12, 0);
        String matricolaDocente = "123456";
        String nomeAula = "Aula 1";
        Prenotazione prenotazione = new Prenotazione(data, oraInizio, oraFine, matricolaDocente, nomeAula);

        assertEquals(oraInizio, prenotazione.getOraInizio());
    }
    
    /***
     * Verifica se il metodo getOraFine() restituisce l'orario di fine corretto
     */
    @Test
    void testGetOraFine() {
        LocalDate data = LocalDate.of(2023, 9, 4);
        LocalTime oraInizio = LocalTime.of(10, 0);
        LocalTime oraFine = LocalTime.of(12, 0);
        String matricolaDocente = "123456";
        String nomeAula = "Aula 1";
        Prenotazione prenotazione = new Prenotazione(data, oraInizio, oraFine, matricolaDocente, nomeAula);

        assertEquals(oraFine, prenotazione.getOraFine());
    }
    
    /***
     * Verifica se il metodo getMatricolaDocente() restituisce la matricola del docente corretta
     */
    @Test
    void testGetMatricolaDocente() {
        LocalDate data = LocalDate.of(2023, 9, 4);
        LocalTime oraInizio = LocalTime.of(10, 0);
        LocalTime oraFine = LocalTime.of(12, 0);
        String matricolaDocente = "123456";
        String nomeAula = "Aula 1";
        Prenotazione prenotazione = new Prenotazione(data, oraInizio, oraFine, matricolaDocente, nomeAula);

        assertEquals(matricolaDocente, prenotazione.getMatricolaDocente());
    }
    
    /***
     * Verifica se il metodo getNomeAula() restituisce il nome dell'aula corretto
     */
    @Test
    void testGetNomeAula() {       
        LocalDate data = LocalDate.of(2023, 9, 4);
        LocalTime oraInizio = LocalTime.of(10, 0);
        LocalTime oraFine = LocalTime.of(12, 0);
        String matricolaDocente = "123456";
        String nomeAula = "Aula 1";
        Prenotazione prenotazione = new Prenotazione(data, oraInizio, oraFine, matricolaDocente, nomeAula);

        assertEquals(nomeAula, prenotazione.getNomeAula());
    }
    
    /***
     * Crea una nuova istanza di Prenotazione con valori validi e verifica il corretto uso del metodo ToString()
     */
    @Test
    public void testToString() {
        LocalDate data = LocalDate.of(2023, 9, 4);
        LocalTime oraInizio = LocalTime.of(10, 0);
        LocalTime oraFine = LocalTime.of(12, 0);
        String matricolaDocente = "123456";
        String nomeAula = "Aula 1";
        Prenotazione prenotazione = new Prenotazione(data, oraInizio, oraFine, matricolaDocente, nomeAula);

        String expectedToString = "Aula: " + nomeAula + "\nGiorno: " + data + "\nOra inizio: " + oraInizio + "\nOra fine: " + oraFine + "\nDocente: " + matricolaDocente + "\n---------------------\n";
        assertEquals(expectedToString, prenotazione.toString());
    }

    
    /***
     * Verifica se il metodo compareTo() restituisce un valore corretto per il confronto tra prenotazioni
     */
    @Test
    public void testCompareTo() {
        // Crea due istanze di Prenotazione con date e orari diversi
        LocalDate data1 = LocalDate.of(2023, 9, 4);
        LocalTime oraInizio1 = LocalTime.of(10, 0);
        LocalTime oraFine1 = LocalTime.of(12, 0);
        String matricolaDocente1 = "123456";
        String nomeAula1 = "Aula 1";
        Prenotazione prenotazione1 = new Prenotazione(data1, oraInizio1, oraFine1, matricolaDocente1, nomeAula1);

        LocalDate data2 = LocalDate.of(2023, 9, 5);
        LocalTime oraInizio2 = LocalTime.of(14, 0);
        LocalTime oraFine2 = LocalTime.of(16, 0);
        String matricolaDocente2 = "789012";
        String nomeAula2 = "Aula 2";
        Prenotazione prenotazione2 = new Prenotazione(data2, oraInizio2, oraFine2, matricolaDocente2, nomeAula2);

        // Verifica che compareTo restituisca un valore negativo se la prima prenotazione viene prima
        assertTrue(prenotazione1.compareTo(prenotazione2) < 0);

        // Verifica che compareTo restituisca un valore positivo se la seconda prenotazione viene prima
        assertTrue(prenotazione2.compareTo(prenotazione1) > 0);

        // Verifica che compareTo restituisca 0 se le prenotazioni hanno la stessa data e orario
        assertTrue(prenotazione1.compareTo(prenotazione1) == 0);
    }

}
