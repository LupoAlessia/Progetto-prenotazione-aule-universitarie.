package upo.universita.modello;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */

public class Utility {
    
    
    
    /***
     * Verifica se la data specificata è nel formato corretto
     * 
     * @param date		la data da verificare
     * @param format	il formato atteso della data
     * @param dateInput	la stringa di input contenente la data
     * @return	true se la data è nel formato corretto, false altrimenti
     */
    static public boolean isValidDateFormat(LocalDate date, String format, String dateInput) {
        if (date == null) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            String formattedDate = date.format(formatter);
            return formattedDate.equals(dateInput);
        } catch (DateTimeException e) {
            return false;
        }
    }
	
	
	/***
     * Verifica se l'ora specificata è nel formato corretto
     * 
     * @param time		l'ora da verificar
     * @param format	il formato atteso dell'ora
     * @param timeInput	la stringa di input contenente l'ora
     * @return	true se l'ora è nel formato corretto, false altrimenti
     */
    static public boolean isValidTimeFormat(LocalTime time, String format, String timeInput) {
        if (time == null) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            String formattedTime = time.format(formatter);
            return formattedTime.equals(timeInput);
        } catch (DateTimeException e) {
            return false;
        }
    }
	
    /***
     * Verifica se una data è nel formato corretto
     * 
     * @param date		la data da verificare
     * @param format	il formato atteso per la data
     * @return	true se la data è nel formato corretto, false altrimenti
     */  
    static public boolean isValidDateFormat(String date, String format) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
	
    /***
     * Converte una stringa nel formato "dd/MM/yyyy" in un oggetto LocalDate
     * 
     * @param dateString	la stringa da convertire
     * @return	l'oggetto LocalDate corrispondente, null se la conversione non è possibile
     */    
    static public LocalDate parseLocalDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    /***
     * Converte una stringa nel formato "HH:mm" in un oggetto LocalTime
     * 
     * @param timeString	 la stringa da convertire
     * @return	l'oggetto LocalTime corrispondente, null se la conversione non è possibile
     */   
    static public LocalTime parseLocalTime(String timeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
