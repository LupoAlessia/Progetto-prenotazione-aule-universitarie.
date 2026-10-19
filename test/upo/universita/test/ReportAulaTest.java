package upo.universita.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import upo.universita.modello.Aula;
import upo.universita.modello.Prenotazione;
import upo.universita.modello.ReportAula;

public class ReportAulaTest {

    private Aula aula;
    private LocalDate date;
    private List<Prenotazione> prenotazioni;
    
    /***
     * Inizializza l'aula, la data e la lista delle prenotazioni per ogni test
     */
    @BeforeEach
    void setUp() { 
        aula = new Aula("Aula 1", 50, Aula.Types.STANDARD);
        date = LocalDate.now();
        prenotazioni = new ArrayList<>();
    }
    
    /***
     * Verifica se il metodo generaReport() nella classe ReportAula genera correttamente un report delle prenotazioni per un'aula specifica
     * ordinato per orario di inizio delle prenotazioni.
     */
    @Test
    void testGeneraReportConPrenotazioniOrdinatePerOrario() {
        // Test con più prenotazioni, il report dovrebbe essere ordinato per orario di inizio
        Prenotazione prenotazione1 = new Prenotazione(date, LocalTime.of(14, 0), LocalTime.of(16, 0), "789123", "Aula 1");
        Prenotazione prenotazione2 = new Prenotazione(date, LocalTime.of(10, 0), LocalTime.of(12, 0), "456789", "Aula 1");
        prenotazioni.add(prenotazione1);
        prenotazioni.add(prenotazione2);
        ReportAula report = new ReportAula(aula, date, prenotazioni);

        // Il report dovrebbe essere ordinato per orario di inizio
        String expected = "Report per l'aula 'Aula 1', in data " + date + "\n";
        expected += "10:00-12:00->456789\n"; 
        expected += "14:00-16:00->789123\n";

        String actual = report.generaReport();
        assertEquals(expected, actual);
    }
}

