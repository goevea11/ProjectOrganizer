package classdiagrampackage;

import gui.*;
import bl.Projekt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik
 * Dieser Dialog bietet die Möglichkeit, ein neues Projekt zu erstellen
 */
public class NeuesProjekt extends javax.swing.JDialog {
    public NeuesProjekt(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        isOk = false;
        dateformat = new SimpleDateFormat("dd.MM.yyyy");
    }
    public boolean isOk;
    public Projekt p;
    private SimpleDateFormat dateformat;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfbeginndatum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfenddatum = new javax.swing.JTextField();
        btnok = new javax.swing.JButton();
        btnabbrechen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(4, 2));

        jLabel1.setText("Name:");
        getContentPane().add(jLabel1);

        tfname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnameActionPerformed(evt);
            }
        });
        getContentPane().add(tfname);

        jLabel2.setText("Beginndatum:");
        getContentPane().add(jLabel2);
        getContentPane().add(tfbeginndatum);

        jLabel3.setText("Enddatum:");
        getContentPane().add(jLabel3);
        getContentPane().add(tfenddatum);

        btnok.setText("OK");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });
        getContentPane().add(btnok);

        btnabbrechen.setText("Abbrechen");
        btnabbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnabbrechenActionPerformed(evt);
            }
        });
        getContentPane().add(btnabbrechen);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnameActionPerformed

    }//GEN-LAST:event_tfnameActionPerformed

    private void btnabbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnabbrechenActionPerformed
        //Sperrt den Zugriff auf das Projekt- Objekt nach dem Klick auf Abbrechen
        isOk = false;
        this.dispose();
    }//GEN-LAST:event_btnabbrechenActionPerformed

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
       //Baut das Projekt- Objekt aus den eingegebenen Daten zusammen
        if (this.tfbeginndatum.getText().charAt(2) == '.' && tfbeginndatum.getText().charAt(5) == '.' && tfbeginndatum.getText().length() == 10
                && this.tfenddatum.getText().charAt(2) == '.' && tfenddatum.getText().charAt(5) == '.' && tfenddatum.getText().length() == 10
                && this.tfname.getText() != null && !this.tfname.getText().equals("")) {

            String projektname = this.tfname.getText();
            String sbeginn = this.tfbeginndatum.getText();
            String sende = this.tfenddatum.getText();

            try {
                p = new Projekt(projektname, dateformat.parse(sbeginn), dateformat.parse(sende));
                isOk = true;
                this.setVisible(false);
            } catch (ParseException ex) {
                Logger.getLogger(NeuesProjekt.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnokActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NeuesProjekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NeuesProjekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NeuesProjekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NeuesProjekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NeuesProjekt dialog = new NeuesProjekt(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnabbrechen;
    private javax.swing.JButton btnok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tfbeginndatum;
    private javax.swing.JTextField tfenddatum;
    private javax.swing.JTextField tfname;
    // End of variables declaration//GEN-END:variables
}
