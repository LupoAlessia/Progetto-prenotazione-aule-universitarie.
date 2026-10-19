package upo.universita.vista;

import javax.swing.*;

import upo.universita.controllo.MainController;
import upo.universita.eccezioni.CercaAuleLibereException;
import upo.universita.eccezioni.PrenotaAulaException;
import upo.universita.eccezioni.StampaReportException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import upo.universita.eccezioni.InserimentoAulaException;
import upo.universita.eccezioni.InserimentoDocenteException;

/***
 * 
 * @author alessia lupo 20040191
 * @author alessandro cadoni 20036246
 *
 */


@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener {
    private MainController controller;
    private JLabel titleLabel;
    private JButton inserisciAuleButton;	
    private JButton auleButton;
    private JButton inserisciDocentiButton; 
    private JButton docentiButton;
    private JButton prenotaButton;
    private JButton aulePrenotateButton;
    private JButton cercaAuleButton;
    private JButton stampaReportButton;
    private JButton esciButton;

    /***
     * Costruttore della classe
     */ 
    public Frame() {
        controller = new MainController(this);
        
       
        setTitle("Sistema di prenotazione delle aule");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 1));
        setPreferredSize(new Dimension(800, 600));	
        
        titleLabel = new JLabel("Benvenuto nel sistema di prenotazione delle aule!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);
        
        
        inserisciAuleButton = new JButton("Inserisci Aule"); 
        inserisciAuleButton.addActionListener(this);
        add(inserisciAuleButton);

        auleButton = new JButton("Visualizza aule disponibili");
        auleButton.addActionListener(this);
        add(auleButton);
        
        
        inserisciDocentiButton = new JButton("Inserisci docenti");
        inserisciDocentiButton.addActionListener(this);
        add(inserisciDocentiButton);

        docentiButton = new JButton("Visualizza docenti disponibili");
        docentiButton.addActionListener(this);
        add(docentiButton);

        prenotaButton = new JButton("Effettua prenotazione");
        prenotaButton.addActionListener(this);
        add(prenotaButton);

        aulePrenotateButton = new JButton("Visualizza aule prenotate con successo");
        aulePrenotateButton.addActionListener(this);
        add(aulePrenotateButton);

        cercaAuleButton = new JButton("Cerca Aule Libere");
        cercaAuleButton.addActionListener(this);
        add(cercaAuleButton);

        stampaReportButton = new JButton("Stampa il report");
        stampaReportButton.addActionListener(this);
        add(stampaReportButton);

        esciButton = new JButton("Esci");
        esciButton.addActionListener(this);
        add(esciButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /***
     * Gestisce gli eventi generati dagli elementi dell'interfaccia.
     * Questo metodo viene chiamato quando un elemento dell'interfaccia utente, come un pulsante, genera un evento.
     * 
     * @param e L'evento generato dall'elemento dell'interfaccia.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	 if (e.getSource() == auleButton) {
             controller.showAuleDisponibili();
         } else if (e.getSource() == docentiButton) {
             controller.showDocentiDisponibili();
         } else if (e.getSource() == inserisciAuleButton) {
            try {
               controller.inserisciAula();						
            } catch (InserimentoAulaException ex) {
               JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
         } else if (e.getSource() == inserisciDocentiButton) {
            try {
               controller.inserisciDocente(); 					
            } catch (InserimentoDocenteException ex) {
               JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
         } else if (e.getSource() == prenotaButton) {
             try {
                 controller.prenotaAula();
             } catch (PrenotaAulaException | IOException exception) {
                 JOptionPane.showMessageDialog(this, exception.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
             }
         } else if (e.getSource() == aulePrenotateButton) {
             controller.showAulePrenotateConSuccesso();
         } else if (e.getSource() == cercaAuleButton) {
            try {
                controller.cercaAuleLibere();
            } catch (CercaAuleLibereException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == stampaReportButton) {
            try {
            	controller.stampaReport();
            } catch (StampaReportException ex) {
            	JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == esciButton) {
            JOptionPane.showMessageDialog(this, "Grazie per aver utilizzato il sistema di prenotazione delle aule.", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    /***
     * Crea un frame per effettuare una prenotazione di un'aula.
     * Questo metodo chiama il controller per gestire la prenotazione dell'aula e gestisce eventuali eccezioni che possono verificarsi.
     */
    public void createPrenotazioneFrame() {
        try {
            controller.prenotaAula();
        } catch (PrenotaAulaException | IOException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /***
     * Aggiorna l'elenco delle aule disponibili e mostra una finestra di dialogo contenente l'elenco.
     * 
     * @param aule Una stringa contenente l'elenco delle aule disponibili
     */
	public void updateAuleDisponibili(String aule) {
        JTextArea auleTextArea = new JTextArea(aule);
        auleTextArea.setEditable(false);	
        JScrollPane scrollPane = new JScrollPane(auleTextArea);	
        
        JOptionPane.showMessageDialog(this, scrollPane, "Aule Disponibili", JOptionPane.INFORMATION_MESSAGE);
    }
	
	/***
	 * Aggiorna l'elenco dei docenti disponibili e mostra una finestra di dialogo contenente l'elenco.
	 * 
	 * @param docenti Una stringa contenente l'elenco dei docenti disponibili
	 */
	public void updateDocentiDisponibili(String docenti) {
		JTextArea docentiTextArea = new JTextArea(docenti);
        docentiTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(docentiTextArea);
        
        JOptionPane.showMessageDialog(this, scrollPane, "Docenti Disponibili", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/***
	 * Aggiorna l'elenco delle aule prenotate con successo e mostra una finestra di dialogo contenente l'elenco.
	 * 
	 * @param aulePrenotate Una stringa contenente l'elenco delle aule prenotate con successo
	 */
	public void updateAulePrenotateConSuccesso(String aulePrenotate) {
	    JTextArea aulePrenotateTextArea = new JTextArea(aulePrenotate);
	    aulePrenotateTextArea.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(aulePrenotateTextArea);
	    
	    JOptionPane.showMessageDialog(this, scrollPane, "Prenotazioni effettuate", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/***
	 * Mostra una finestra contenente l'elenco delle aule disponibili.
	 * 
	 * @param aule Una stringa contenente l'elenco delle aule disponibili
	 */
    public void showAuleDisponibiliWindow(String aule) {
        JFrame auleWindow = new JFrame();
        auleWindow.setTitle("Aule disponibili");
        auleWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        auleWindow.setSize(400, 300);

        JTextArea auleTextArea = new JTextArea(aule);
        auleTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(auleTextArea);
        auleWindow.add(scrollPane);

        auleWindow.setVisible(true);
    }
	
    /***
     * Mostra una finestra contenente l'elenco dei docenti disponibili.
     * 
     * @param docenti Una stringa contenente l'elenco dei docenti disponibili
     */
    public void showDocentiDisponibiliWindow(String docenti) {
        JFrame docentiWindow = new JFrame();
        docentiWindow.setTitle("Docenti disponibili");
        docentiWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        docentiWindow.setSize(600, 300);

        JTextArea docentiTextArea = new JTextArea(docenti);
        docentiTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(docentiTextArea);
        docentiWindow.add(scrollPane);	//aggiunta Jscroll alla finestra

        docentiWindow.setVisible(true);
    }
    
    /***
     * Mostra una finestra contenente l'elenco delle aule prenotate con successo.
     * 
     * @param aulePrenotate Una stringa contenente l'elenco delle aule prenotate con successo
     */
    public void showAulePrenotateConSuccessoWindow(String aulePrenotate) {
        JFrame aulePrenotateWindow = new JFrame();
        aulePrenotateWindow.setTitle("Docenti disponibili");
        aulePrenotateWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aulePrenotateWindow.setSize(600, 300);

        JTextArea docentiTextArea = new JTextArea(aulePrenotate);
        docentiTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(docentiTextArea);
        aulePrenotateWindow.add(scrollPane);

        aulePrenotateWindow.setVisible(true);
    }
}