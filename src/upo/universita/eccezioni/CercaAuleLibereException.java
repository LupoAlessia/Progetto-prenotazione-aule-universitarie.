package upo.universita.eccezioni;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

@SuppressWarnings("serial")
public class CercaAuleLibereException extends Exception {

	
	/***
	 * Crea un'eccezione personalizzata per la ricerca delle aule libere
	 * 
	 * @param message	Messaggio di errore
	 */
	public CercaAuleLibereException(String message) {
		super(message);
	}

}
