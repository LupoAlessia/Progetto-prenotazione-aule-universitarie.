package upo.universita.vista;

import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */
public class MainView {
	
	/***
	 * Metodo principale dell'applicazione
	 */
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> {
	        Frame frame = new Frame();
	        ImageIcon icon = new ImageIcon("upo.jpeg");
	        frame.setIconImage(icon.getImage());
	        frame.setVisible(true);
	    });
	}
}
