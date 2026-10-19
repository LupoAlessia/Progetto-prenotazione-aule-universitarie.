package upo.universita.eccezioni;

import java.io.IOException;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

@SuppressWarnings("serial")
public class InserimentoDocenteException  extends Exception {
	
    /***
     * Crea un'eccezione personalizzata per l'errore di inserimento del docente
     *
     * @param message 	Messaggio di errore
     */
     public InserimentoDocenteException(String message) {
         super(message);
     }

     /***
      * Crea un'eccezione personalizzata per l'errore di inserimento del docente con una causa specifica
      * 
      * @param message 	Messaggio di errore
      * @param cause	La causa dell'eccezione
      */
     public InserimentoDocenteException(String message, IOException cause) {
         super(message, cause);
     } 
}
