package upo.universita.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.universita.modello.Docente;
import upo.universita.modello.Prenotazione;
import upo.universita.modello.ReportDocente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReportDocenteTest {

    private Docente docente;
    private LocalDate date;
    private List<Prenotazione> prenotazioni;
    
    /***
     * Inizializza il docente, la data e la lista delle prenotazioni per ogni test
     */
    @BeforeEach
    void setUp() {
        docente = new Docente("Mario", "Rossi", "123456");
        date = LocalDate.now();
        prenotazioni = new ArrayList<>();
    }
    
    /**
     * Verifica se il metodo generaReport() nella classe ReportDocente genera correttamente un report delle prenotazioni per un docente specifico
     * ordinato per orario di inizio delle prenotazioni.
     */
    @Test
    void testGeneraReportConPrenotazioniOrdinatePerOrario() {
        // Test con più prenotazioni, il report dovrebbe essere ordinato per orario di inizio
        Prenotazione prenotazione1 = new Prenotazione(date, LocalTime.of(14, 0), LocalTime.of(16, 0), "123456", "Aula 1");
        Prenotazione prenotazione2 = new Prenotazione(date, LocalTime.of(10, 0), LocalTime.of(12, 0), "123456", "Aula 2");
        prenotazioni.add(prenotazione1);
        prenotazioni.add(prenotazione2);
        ReportDocente report = new ReportDocente(docente, date, prenotazioni);

        // Il report dovrebbe essere ordinato per orario di inizio
        String expected = "Report per il docente '123456', in data " + date + "\n";
        expected += "10:00-12:00->Aula 2\n";
        expected += "14:00-16:00->Aula 1\n";

        String actual = report.generaReport();
        assertEquals(expected, actual);
    }
}
