package upo.universita.modello;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class ReportDocente implements Report{
	
	Docente docente;
	LocalDate date;
	List<Prenotazione> prenotazioni; 
	
	/***
	 * Costruttore della classe ReportDocente
	 * 
	 * @param docente		Il docente a cui si riferisce il report
	 * @param date			La data del report
	 * @param prenotazioni	Elenco delle prenotazioni effettuate dal docente nella data specificata
	 */	
	public ReportDocente(Docente docente, LocalDate date, List<Prenotazione> prenotazioni) {
		this.docente = docente;
		this.date = date;
		this.prenotazioni = prenotazioni;
	}
	
	/***
	 * Genera il report delle prenotazioni effettuate dal docente nella data specificata
	 * 
	 * @return	Una stringa contenente il report delle prenotazioni in ordine di fascia di orario
	 */	
	@Override
	public String generaReport() {
		
		Collections.sort(prenotazioni);
		String report = "Report per il docente '" + this.docente.getMatricola() + "', in data " + this.date + "\n";
		
		for(Prenotazione p: prenotazioni) {
			report += p.getOraInizio().toString() + "-" + p.getOraFine().toString() + "->" + p.getNomeAula() + "\n";
		}
		
		return report;
	}
}
