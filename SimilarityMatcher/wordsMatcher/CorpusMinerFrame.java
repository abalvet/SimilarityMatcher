/*
 * Created on 5 sept. 06
 * Créé le 5 sept. 06
 */
package wordsMatcher;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class CorpusMinerFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JMenuBar CorpusMinerJMenuBar = null;

    private JMenu OpenMenu = null;

    private JMenuItem OpenItem = null;

    private JMenuItem SaveMenuItem = null;

    private JMenuItem CloseMenuItem = null;

    private JTextPane CorpusMinerTextPane = null;

    /**
     * This method initializes CorpusMinerJMenuBar	
     * 	
     * @return javax.swing.JMenuBar	
     */
    private JMenuBar getCorpusMinerJMenuBar()
        {
            if (CorpusMinerJMenuBar == null)
                {
                    CorpusMinerJMenuBar = new JMenuBar();
                    CorpusMinerJMenuBar.add(getOpenMenu());
                }
            return CorpusMinerJMenuBar;
        }

    /**
     * This method initializes OpenMenu	
     * 	
     * @return javax.swing.JMenu	
     */
    private JMenu getOpenMenu()
        {
            if (OpenMenu == null)
                {
                    OpenMenu = new JMenu();
                    OpenMenu.setText("Open");
                    OpenMenu.add(getOpenItem());
                    OpenMenu.add(getSaveMenuItem());
                    OpenMenu.add(getCloseMenuItem());
                }
            return OpenMenu;
        }

    /**
     * This method initializes OpenItem	
     * 	
     * @return javax.swing.JMenuItem	
     */
    private JMenuItem getOpenItem()
        {
            if (OpenItem == null)
                {
                    OpenItem = new JMenuItem();
                    OpenItem.setText("Open File");
                }
            return OpenItem;
        }

    /**
     * This method initializes SaveMenuItem	
     * 	
     * @return javax.swing.JMenuItem	
     */
    private JMenuItem getSaveMenuItem()
        {
            if (SaveMenuItem == null)
                {
                    SaveMenuItem = new JMenuItem();
                    SaveMenuItem.setText("Save");
                }
            return SaveMenuItem;
        }

    /**
     * This method initializes CloseMenuItem	
     * 	
     * @return javax.swing.JMenuItem	
     */
    private JMenuItem getCloseMenuItem()
        {
            if (CloseMenuItem == null)
                {
                    CloseMenuItem = new JMenuItem();
                    CloseMenuItem.setText("Close");
                }
            return CloseMenuItem;
        }

    /**
     * This method initializes CorpusMinerTextPane	
     * 	
     * @return javax.swing.JTextPane	
     */
    private JTextPane getCorpusMinerTextPane()
        {
            if (CorpusMinerTextPane == null)
                {
                    CorpusMinerTextPane = new JTextPane();  
                    CorpusMinerTextPane.setToolTipText("Text to process");
                }
            return CorpusMinerTextPane;
        }

    /**
     * @param args
     */
    public static void main(String[] args)
        {
            // TODO Auto-generated method stub

            SwingUtilities.invokeLater(new Runnable() {
                public void run()
                    {
                        CorpusMinerFrame thisClass = new CorpusMinerFrame();
                        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        thisClass.setVisible(true);
                    }
            });
        }

    /**
     * This is the default constructor
     */
    public CorpusMinerFrame() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize()
        {
            this.setSize(370, 271);
            this.setJMenuBar(getCorpusMinerJMenuBar());
            this.setContentPane(getJContentPane());
            this.setTitle("CorpusMiner");
        }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane()
        {
            if (jContentPane == null)
                {
                    jContentPane = new JPanel();
                    jContentPane.setLayout(new BorderLayout());
                    jContentPane.add(getCorpusMinerTextPane(), BorderLayout.CENTER);
                }
            return jContentPane;
        }

}  //  @jve:decl-index=0:visual-constraint="10,10"
