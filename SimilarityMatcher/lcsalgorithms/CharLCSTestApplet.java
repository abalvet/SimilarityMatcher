/*
 * Created on 11 sept. 06
 * Créé le 11 sept. 06
 */
package lcsalgorithms;


import java.awt.Dimension;

//import javax.swing.JApplet;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 * 
 * @author Balvet Antonio
 * Applet de test de l'algorithme LCS en mode caractères. 
 */
public class CharLCSTestApplet extends javax.swing.JApplet {
    private JTextArea jTextArea1;
    private JTextArea jTextArea2;
    private JButton jButton1;
    private JTextPane jTextPane1;
    OmniLCS _olcs = new OmniLCS();
    /**
    * Auto-generated main method to display this 
    * JApplet inside a new JFrame.
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CharLCSTestApplet inst = new CharLCSTestApplet();
        frame.getContentPane().add(inst);
        ((JComponent)frame.getContentPane()).setPreferredSize(inst.getSize());
        frame.pack();
        frame.setVisible(true);
        OmniLCS tt = new OmniLCS();        
    }
    
    public CharLCSTestApplet() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            this.getContentPane().setLayout(null);
            this.setSize(684, 508);
            this.setName("LCS en mode caracères");
            
            {
                jTextArea1 = new JTextArea();
                this.getContentPane().add(getJTextArea1());
                jTextArea1.setText("ABC");
                jTextArea1.setBounds(13, 8, 657, 111);
                jTextArea1.setToolTipText("Entrer la 1ère chaîne");
                jTextArea1.setAutoscrolls(true);
                jTextArea1.setLineWrap(true);
            }
            {
                jTextArea2 = new JTextArea();
                this.getContentPane().add(getJTextArea2());
                jTextArea2.setText("WAXBZYCM");
                jTextArea2.setBounds(11, 144, 661, 111);
                jTextArea2.setAutoscrolls(true);
                jTextArea2.setLineWrap(true);
                jTextArea2.setToolTipText("Entrer la 2ème chaîne");
            }
            {
                jTextPane1 = new JTextPane();
                this.getContentPane().add(jTextPane1);
                jTextPane1.setText("LCS en mode caractères");
                jTextPane1.setBounds(13, 365, 656, 132);
                jTextPane1.setAutoscrolls(true);
                jTextPane1.setEditable(false);
                jTextPane1.setName("Résultat");
                jTextPane1.setToolTipText("Résultat du calcul des plus longues sous-chaînes communes");
            }
            {
                jButton1 = new JButton();
                this.getContentPane().add(jButton1);
                jButton1.setLayout(null);
                jButton1.setText("Calcul");
                jButton1.setBounds(295, 312, 69, 26);
                jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JTextArea getJTextArea1() {
        return jTextArea1;
    }
    
    /**
    * Auto-generated method for setting the popup menu for a component
    */
    private void setComponentPopupMenu(final java.awt.Component parent, final javax.swing.JPopupMenu menu) {
parent.addMouseListener(new java.awt.event.MouseAdapter() {
public void mousePressed(java.awt.event.MouseEvent e) {
if(e.isPopupTrigger())
menu.show(parent, e.getX(), e.getY());
}
public void mouseReleased(java.awt.event.MouseEvent e) {
if(e.isPopupTrigger())
menu.show(parent, e.getX(), e.getY());
}
});
    }
    
    public JTextArea getJTextArea2() {
        return jTextArea2;
    }
    
    private void jButton1ActionPerformed(ActionEvent evt) {
    //System.out.println("jButton1.actionPerformed, event="+evt);
//TODO add your code for jButton1.actionPerformed
    String x = this.jTextArea1.getText();
    String y = this.jTextArea2.getText();
    _olcs.initialise(x, y, true, "");
    String xx = _olcs.printCharTable(_olcs._aCharPattern);
    String yy = _olcs.printCharTable(_olcs._bCharPattern);
    String zz = _olcs.printStringTable(_olcs._intersectionPattern);
    String out = "LCS\n" + "X:\t" + xx + "\n" + "Y:\t" + yy + "\nInter:\t" + zz;
    this.jTextPane1.setText(out);
    }

}

