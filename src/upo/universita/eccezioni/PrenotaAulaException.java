package upo.universita.eccezioni;

import java.io.IOException;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

@SuppressWarnings("serial")
public class PrenotaAulaException extends Exception {

	
	/***
	 * Crea un'eccezione personalizzata per l'errore di prenotazione dell'aula
	 *
	 * @param message 	Messaggio di errore
	 */
	 public PrenotaAulaException(String message) {
	     super(message);
	 }
	 
	 /***
	  * Crea un'eccezione personalizzata per l'errore di prenotazione dell'aula con una causa specifica
	  * 
	  * @param message 	Messaggio di errore
	  * @param cause	La causa dell'eccezione
	  */
	 public PrenotaAulaException(String message, IOException cause) {
	     super(message, cause);
	 }
}