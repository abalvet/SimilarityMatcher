

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
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* *************************************
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
* for this machine, so Jigloo or this code cannot be used legally
* for any corporate or commercial purpose.
* *************************************
*/
public class WordLCSJApplet extends javax.swing.JApplet {
	private JTextArea jTextArea1;
	private JTextArea jTextArea2;
	private JButton jButton1;
	private JTextPane jTextPane1;

	/**
	* Auto-generated main method to display this 
	* JApplet inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		WordLCSJApplet inst = new WordLCSJApplet();
		frame.getContentPane().add(inst);
		((JComponent)frame.getContentPane()).setPreferredSize(inst.getSize());
		frame.pack();
		frame.setVisible(true);
	}
	
	public WordLCSJApplet() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.getContentPane().setLayout(null);
			this.setSize(684, 508);
            {
                jTextArea1 = new JTextArea();
                this.getContentPane().add(getJTextArea1());
                jTextArea1.setText("Quelle est la capitale du Burundi ?");
                jTextArea1.setBounds(13, 8, 657, 111);
                jTextArea1.setToolTipText("Entrer la 1�re cha�ne");
                jTextArea1.setAutoscrolls(true);
                jTextArea1.setLineWrap(true);
            }
            {
                jTextArea2 = new JTextArea();
                this.getContentPane().add(getJTextArea2());
                jTextArea2.setText("Quelle est la capitale officielle du Burundi ?");
                jTextArea2.setBounds(11, 144, 661, 111);
                jTextArea2.setAutoscrolls(true);
                jTextArea2.setLineWrap(true);
                jTextArea2.setToolTipText("Entrer la 2ème chaîne");
            }
            {
                jTextPane1 = new JTextPane();
                this.getContentPane().add(jTextPane1);
                jTextPane1.setText("CLCS & DLCS");
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
    System.out.println("jButton1.actionPerformed, event="+evt);
//TODO add your code for jButton1.actionPerformed
	WordLCS_V1 wl = new WordLCS_V1();
	String a = this.jTextArea1.getText();
	String b = this.jTextArea2.getText();
	String[] aa ;
	String[] bb ;
	aa = a.split(" ");
	bb = b.split(" ");
    String[] aab;
    String[] bbb;
    int maxl = 0;
    if(aa.length > bb.length)
        {
        maxl = aa.length;
        bbb = new String[maxl];
        for(int ind = 0; ind < bb.length; ind ++)
            {
            bbb[ind] = bb[ind];
            }
        for(int n = bb.length ; n < maxl; n++)
            {
            bbb[n] = "_";
            }
        wl.buildTables(aa,bbb);
        }
    else
        {
        maxl = bb.length;
        aab = new String[maxl];
        for(int inda = 0; inda < aa.length; inda++)
            {
            aab[inda] = aa[inda];
            }
        for(int m = aa.length; m < maxl; m++)
            {
            System.out.println("aab= " + "_");
            aab[m] = "_";
            }
        wl.buildTables(bb,aab);
        }
    String res = 	"CLCS:\t" + wl._clcs + "\n" 
    				+
    				"DLCS:\t" + wl._dlcs;
    this.jTextPane1.setText(res);
    }

}
