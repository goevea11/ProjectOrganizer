/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bl.Arbeitsschritt;
import bl.Mitarbeiter;
import bl.Projekt;
import database.DBAccess;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Domi
 */
public class NewArbeitsschritt extends javax.swing.JDialog {

    /**
     * Creates new form NewArbeitsschritt
     */
    public boolean isOk;
    public Arbeitsschritt a;
    private Mitarbeiter m;
    private DBAccess dbaccess;
    private LinkedList<Mitarbeiter> alleMitarbeiter;
    private Projekt p;

    public NewArbeitsschritt(java.awt.Frame parent, boolean modal, DBAccess dba) {
        super(parent, modal);
        initComponents();
        isOk = false;
        dbaccess = dba;
        this.alleMitarbeiter = dbaccess.getAllMitarbeiter();
        //ComboBox befüllen
        String[] mitarbeiterarray = new String[alleMitarbeiter.size()];
        for (int i = 0; i < mitarbeiterarray.length; i++) {
            mitarbeiterarray[i] = alleMitarbeiter.get(i).getId() + ", " + alleMitarbeiter.get(i).getNachname();
            this.cbMitarbeiter.addItem(mitarbeiterarray[i]);
        }

    }

    public Mitarbeiter getMitarbeiter() {
        //Mitarbeiter zurückgeben, der für den Arbeitsschritt ausgewählt wurde
        return m;
    }

    void setProjekt(Projekt p) {
        this.p = p;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labbezeichnung = new javax.swing.JLabel();
        tfBezeichnung = new javax.swing.JTextField();
        labText = new javax.swing.JLabel();
        tfText = new javax.swing.JTextField();
        labMitarbeiter = new javax.swing.JLabel();
        cbMitarbeiter = new javax.swing.JComboBox();
        btnabbrechen = new javax.swing.JButton();
        btnok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Neuen Arbeitsschritt einfügen");

        jPanel1.setLayout(new java.awt.GridLayout(4, 0));

        labbezeichnung.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labbezeichnung.setText("Bezeichnung:");
        jPanel1.add(labbezeichnung);

        tfBezeichnung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBezeichnungActionPerformed(evt);
            }
        });
        jPanel1.add(tfBezeichnung);

        labText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labText.setText("Text:");
        jPanel1.add(labText);

        tfText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTextActionPerformed(evt);
            }
        });
        jPanel1.add(tfText);

        labMitarbeiter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labMitarbeiter.setText("zuständiger Mitarbeiter:");
        jPanel1.add(labMitarbeiter);

        jPanel1.add(cbMitarbeiter);

        btnabbrechen.setText("Abbrechen");
        btnabbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnabbrechenActionPerformed(evt);
            }
        });
        jPanel1.add(btnabbrechen);

        btnok.setText("OK");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });
        jPanel1.add(btnok);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfBezeichnungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBezeichnungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBezeichnungActionPerformed

    private void tfTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTextActionPerformed

    private void btnabbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnabbrechenActionPerformed
        this.isOk = false;
        this.dispose();
    }//GEN-LAST:event_btnabbrechenActionPerformed

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        if (this.tfBezeichnung.getText() != null && !tfBezeichnung.getText().equals("")
                && this.tfText.getText() != null && !tfText.getText().equals("")) {
           //Arbeitsschritt zusammenbauen
            //int arbeitsschrittid, int projectid, int progressid, String bezeichnung, String text
            String bezeichnung = this.tfBezeichnung.getText();
            String text = this.tfText.getText();
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            this.a = new Arbeitsschritt(this.dbaccess.getArbeitsschrittId(bezeichnung, p.getProjektid()), p.getProjektid(), 0, bezeichnung, text);
            int mitarbeiterid = Integer.parseInt(this.cbMitarbeiter.getSelectedItem().toString().split(",")[0]);
            System.out.println(mitarbeiterid);
           //ausgewälten Mitarbeiter suchen

            for (int i = 0; i < this.alleMitarbeiter.size(); i++) {
                if (alleMitarbeiter.get(i).getId() == mitarbeiterid) {
                    m = alleMitarbeiter.get(i);
                }
            }

            if (this.m != null && this.a != null) {
                this.isOk = true;
                this.setVisible(false);
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
            java.util.logging.Logger.getLogger(NewArbeitsschritt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewArbeitsschritt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewArbeitsschritt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewArbeitsschritt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewArbeitsschritt dialog = new NewArbeitsschritt(new javax.swing.JFrame(), true, new DBAccess("proorg"));
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
    private javax.swing.JComboBox cbMitarbeiter;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labMitarbeiter;
    private javax.swing.JLabel labText;
    private javax.swing.JLabel labbezeichnung;
    private javax.swing.JTextField tfBezeichnung;
    private javax.swing.JTextField tfText;
    // End of variables declaration//GEN-END:variables

}
