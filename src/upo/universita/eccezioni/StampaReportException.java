package upo.universita.eccezioni;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

@SuppressWarnings("serial")
public class StampaReportException extends Exception {

	
	/***
	 * Crea un'eccezione personalizzata per la stampa dei report
	 * 
	 * @param message 	Messaggio di errore
	 */	
	public StampaReportException(String message) {
		super(message);
	}

}
