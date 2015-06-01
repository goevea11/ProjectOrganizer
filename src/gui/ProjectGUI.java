package gui;

import bl.Mitarbeiter;
import bl.MitarbeiterTableModel;
import bl.ProjectTableModel;
import bl.Projekt;
import database.DBAccess;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dominik
 * In dieser GUI werden die Projekte angezeigt, 
 * denen der Mitarbeiter zugeteilt ist, welcher sich vorher angemeldet hat.
 * Man kann auch ein neues Projekt erstellen, 
 * für welches man dann logischerweise als einziger und als Gründer eingetragen ist
 */
public class ProjectGUI extends javax.swing.JFrame {

    
    
    private LinkedList<Projekt> ll;
    private DBAccess dba;
    private ProjectTableModel prtablemodel;
    private NeuesProjekt newprojectdialog;
    private MitarbeiterTableModel mittablemodel;
    private int gründerid;
    int letzterklick = -1;

    public ProjectGUI(int mid) {
        initComponents();
        //Konstruktor, welcher die DB-Verbindung aufbaut, 
        TaskboardGUI tgui;
        gründerid = mid;
        dba = new DBAccess("proorg");
        newprojectdialog = new NeuesProjekt(this, true);
        
        //Holt eine Liste mit allen Projekten, an denen ein Mitarbeiter beteiligt ist
        ll = dba.getProjekte(mid);
        
        mittablemodel = new MitarbeiterTableModel(new LinkedList<Mitarbeiter>());
        prtablemodel = new ProjectTableModel(ll);
        tabprojekte.setModel(prtablemodel);
        this.tabmitarbeiter.setModel(mittablemodel);
        

    }

    void setDBAccess(DBAccess access) {
        this.dba = access;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabprojekte = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabmitarbeiter = new javax.swing.JTable();
        panbtns = new javax.swing.JPanel();
        btncreateProjekt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(464, 400));

        jSplitPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jSplitPane1.setDividerLocation(-80);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Projekte"));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(231, 211));

        tabprojekte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabprojekte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabprojekteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabprojekteMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabprojekte);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Beteiligte Mitarbeiter"));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(231, 211));

        tabmitarbeiter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabmitarbeiter.setMaximumSize(new java.awt.Dimension(300, 64));
        jScrollPane2.setViewportView(tabmitarbeiter);

        jSplitPane1.setRightComponent(jScrollPane2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        panbtns.setLayout(new java.awt.GridLayout(1, 2));

        btncreateProjekt.setText("Erstelle neues Projekt");
        btncreateProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreateProjektActionPerformed(evt);
            }
        });
        panbtns.add(btncreateProjekt);

        getContentPane().add(panbtns, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void tabprojekteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabprojekteMouseClicked
        /*
        Methode, die beim Klick auf die obere Tabelle (Auflistung der Projekte) ausgelöst wird.
        1. Klick:
        Die zum Projekt zugehörigen Mitarbeiter werden im unteren Teil der GUI angezeigt
        2. Klick:
        Man kommt zur TaskboardGUI, in welcher alle Arbeitsschritte(+Beschreibung, zugeteilter Mitarbeiter, Fertigstellungsgrad) 
        */
        JTable t = (JTable) evt.getComponent();
        int row = t.getSelectedRow();
        int prid = (int) this.prtablemodel.getValueAt(row, 0);
        if (this.letzterklick == row) {
            //Taskboardgui öffnen, wenn 2 mal geklickt
            TaskboardGUI tgui = new TaskboardGUI(ll.get(row));
            tgui.setVisible(true);

        } else {

            try {
                //Zeigt die zugehörigen Mitarbeiter zum Projekt an
                this.mittablemodel.setlist(dba.getMitarbeiterfromProjekt(prid));
                this.tabmitarbeiter.setModel(mittablemodel);

                letzterklick = row;
            } catch (SQLException ex) {
                Logger.getLogger(ProjectGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_tabprojekteMouseClicked


    private void btncreateProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateProjektActionPerformed
        //Hier wird der Dialog zur Erstellung eines neuen Projektes aufgerufen
        this.newprojectdialog.setVisible(true);

        if (newprojectdialog.isOk) {
            Projekt p = newprojectdialog.p;
            this.dba.insertProjekt(p, gründerid);
        }
    }//GEN-LAST:event_btncreateProjektActionPerformed

    private void tabprojekteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabprojekteMouseEntered


    }//GEN-LAST:event_tabprojekteMouseEntered

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
            java.util.logging.Logger.getLogger(ProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectGUI(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncreateProjekt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel panbtns;
    private javax.swing.JTable tabmitarbeiter;
    private javax.swing.JTable tabprojekte;
    // End of variables declaration//GEN-END:variables

}
