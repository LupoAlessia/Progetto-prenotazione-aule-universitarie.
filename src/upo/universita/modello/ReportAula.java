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


public class ReportAula implements Report {

	Aula aula;
	LocalDate date;
	List<Prenotazione> prenotazioni;
	
	/***
	 * Costruttore della classe ReportAula
	 * 
	 * @param aula			L'aula a cui si riferisce il report
	 * @param date			La data del report
	 * @param prenotazioni	Elenco delle prenotazioni per aula e data specificate
	 */	
	public ReportAula(Aula aula, LocalDate date, List<Prenotazione> prenotazioni) {
		this.aula = aula;
		this.date = date;
		this.prenotazioni = prenotazioni;
	}

	/***
	 * Genera il report delle prenotazioni per l'aula e la data specificate
	 * 
	 * @return Una stringa contenente il report delle prenotazioni in ordine di fascia di orario
	 */	
	@Override
	public String generaReport() {
		
		Collections.sort(prenotazioni);
		String report = "Report per l'aula '" + this.aula.getNome() + "', in data " + this.date + "\n";
		
		for(Prenotazione p: prenotazioni) {
			report += p.getOraInizio().toString() + "-" + p.getOraFine().toString() + "->" + p.getMatricolaDocente() + "\n";
		}
		
		return report;
	}
}
