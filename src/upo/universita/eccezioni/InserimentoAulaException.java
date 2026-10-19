package upo.universita.eccezioni;

import java.io.IOException;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

@SuppressWarnings("serial")
public class InserimentoAulaException extends Exception{
	
    /***
     * Crea un'eccezione personalizzata per l'errore di inserimento dell'aula
     *
     * @param message 	Messaggio di errore
     */
     public InserimentoAulaException(String message) {
         super(message);
     }

     /***
      * Crea un'eccezione personalizzata per l'errore di inserimento dell'aula con una causa specifica
      * 
      * @param message 	Messaggio di errore
      * @param cause	La causa dell'eccezione
      */
     public InserimentoAulaException(String message, IOException cause) {
         super(message, cause);
     }
}
