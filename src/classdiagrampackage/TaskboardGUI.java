/*
 * Im Arbeitsboard werden die verschiedenen Arbeitsschritte verwaltet
 Es gibt 3 Felder in denen die Arbeitsschritte mit der ID und einer Bezeichnung
 abgebildet sind. Wählt man eines aus, kann man die Genaue Beschreibung
 und den zuständigen Mitarbeiter ansehen.
 */
package classdiagrampackage;

import gui.*;
import bl.Projekt;
import database.DBAccess;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import bl.Arbeitsschritt;
import bl.Mitarbeiter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;

/**
 *
 * @author Veronika
 */
public class TaskboardGUI extends javax.swing.JFrame {

    private DefaultListModel todomodel;
    private DefaultListModel inworkmodel;
    private DefaultListModel finishedmodel;
    private DBAccess dba;
    private LinkedList<Arbeitsschritt> todolist;
    private LinkedList<Arbeitsschritt> inworklist;
    private LinkedList<Arbeitsschritt> finishedlist;
    private Projekt p;
    private NewArbeitsschritt newarbeitsschrittdialog;

    public TaskboardGUI(Projekt projekt) {
        initComponents();
        // Initialisierung der Methoden
        p = projekt;
        todomodel = new DefaultListModel();
        this.todoList.setModel(todomodel);
        inworkmodel = new DefaultListModel();
        this.inworkList.setModel(inworkmodel);
        finishedmodel = new DefaultListModel();
        this.finishedList.setModel(finishedmodel);
        dba = new DBAccess("proorg");
        todolist = dba.getToDoList(p.getProjektid());
        inworklist = dba.getInWorkList(p.getProjektid());
        finishedlist = dba.getFinishedList(p.getProjektid());
        todomodel = write(todolist, todomodel);
        inworkmodel = write(inworklist, inworkmodel);
        finishedmodel = write(finishedlist, finishedmodel);
        newarbeitsschrittdialog = new NewArbeitsschritt(this, true, dba);
        newarbeitsschrittdialog.setProjekt(p);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        todoList = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        todo_inwork_right = new javax.swing.JButton();
        todo_inwork_left = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inworkList = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        inwork_finished_right = new javax.swing.JButton();
        inwork_finished_left = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        finishedList = new javax.swing.JList();
        jPanel7 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        btnaddArbeitsschritt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(2, 5));

        jPanel6.setLayout(new java.awt.GridLayout(1, 5));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("TO DO"));

        todoList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        todoList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                todoListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(todoList);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        todo_inwork_right.setText("->");
        todo_inwork_right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todo_inwork_rightActionPerformed(evt);
            }
        });
        jPanel2.add(todo_inwork_right, new java.awt.GridBagConstraints());

        todo_inwork_left.setText("<-");
        todo_inwork_left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todo_inwork_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel2.add(todo_inwork_left, gridBagConstraints);

        jPanel6.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("IN ARBEIT"));

        inworkList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        inworkList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inworkListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(inworkList);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        inwork_finished_right.setText("->");
        inwork_finished_right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inwork_finished_rightActionPerformed(evt);
            }
        });
        jPanel4.add(inwork_finished_right, new java.awt.GridBagConstraints());

        inwork_finished_left.setText("<-");
        inwork_finished_left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inwork_finished_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel4.add(inwork_finished_left, gridBagConstraints);

        jPanel6.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("FERTIG"));

        finishedList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        finishedList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                finishedListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(finishedList);

        jPanel5.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel5);

        getContentPane().add(jPanel6);

        jPanel7.setLayout(new java.awt.GridLayout(2, 0));
        jPanel7.add(jTextField1);

        btnaddArbeitsschritt.setText("Arbeitsschritt hinzufügen");
        btnaddArbeitsschritt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddArbeitsschrittActionPerformed(evt);
            }
        });
        jPanel7.add(btnaddArbeitsschritt);

        getContentPane().add(jPanel7);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void todo_inwork_rightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todo_inwork_rightActionPerformed
        // Es wird der angeklickte Arbeitsschritt ermittelt
        int i = this.todoList.getSelectedIndex();
        // Ist der Selectierte Index -1, wurde in einer falschen JList etwas angeklickt
        // dieser Fehler wird in dem Textfeld ausgegeben
        if (i != -1) {
            String s = (String) this.todomodel.getElementAt(i);
            String[] sf = s.split(",");
            sf[0] = sf[0].trim();
            // Die Process ID des Arbeitsschrittes wird geändert
            boolean b = dba.updateArbeitsschritt(p.getProjektid(), sf[0], 1);
            if (b) {
                this.jTextField1.setText("successful!");
            }
            // Die Model werden als erstes geleert und dann neu befüllt
            inworkmodel.clear();
            todomodel.clear();
            this.todolist = dba.getToDoList(this.p.getProjektid());
            this.inworklist = dba.getInWorkList(this.p.getProjektid());
            inworkmodel = this.write(inworklist, inworkmodel);
            todomodel = this.write(todolist, todomodel);
        }
        else
        {
            this.jTextField1.setText("Falsche Liste ausgewählt");
        }
    }//GEN-LAST:event_todo_inwork_rightActionPerformed

    private void todo_inwork_leftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todo_inwork_leftActionPerformed
        // Es wird der angeklickte Arbeitsschritt ermittelt
        int i = this.inworkList.getSelectedIndex();
        // Ist der Selectierte Index -1, wurde in einer falschen JList etwas angeklickt
        // dieser Fehler wird in dem Textfeld ausgegeben
        if (i != -1) {
            String s = (String) this.inworkmodel.getElementAt(i);
            String[] sf = s.split(",");
            sf[0] = sf[0].trim();
            // Die Process ID des Arbeitsschrittes wird geändert
            boolean b = dba.updateArbeitsschritt(p.getProjektid(), sf[0], 0);
            if (b) {
                this.jTextField1.setText("successful!");
            }
            // Die Model werden als erstes geleert und dann neu befüllt
            inworkmodel.clear();
            todomodel.clear();
            this.todolist = dba.getToDoList(this.p.getProjektid());
            this.inworklist = dba.getInWorkList(this.p.getProjektid());
            inworkmodel = this.write(inworklist, inworkmodel);
            todomodel = this.write(todolist, todomodel);
        }
        else
        {
            this.jTextField1.setText("Falsche Liste ausgewählt");
        }
    }//GEN-LAST:event_todo_inwork_leftActionPerformed

    private void inwork_finished_rightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inwork_finished_rightActionPerformed
        // Es wird der angeklickte Arbeitsschritt ermittelt
        int i = this.inworkList.getSelectedIndex();
        // Ist der Selectierte Index -1, wurde in einer falschen JList etwas angeklickt
        // dieser Fehler wird in dem Textfeld ausgegeben
        if (i != -1) {
            String s = (String) this.inworkmodel.getElementAt(i);
            String[] sf = s.split(",");
            sf[0] = sf[0].trim();
            // Die Process ID des Arbeitsschrittes wird geändert
            boolean b = dba.updateArbeitsschritt(p.getProjektid(), sf[0], 2);
            if (b) {
                this.jTextField1.setText("successful!");
            }

            // Die Model werden als erstes geleert und dann neu befüllt
            inworkmodel.clear();
            finishedmodel.clear();
            this.finishedlist = dba.getFinishedList(p.getProjektid());
            this.inworklist = dba.getInWorkList(this.p.getProjektid());
            inworkmodel = this.write(inworklist, inworkmodel);
            finishedmodel = this.write(finishedlist, finishedmodel);
        }
        else
        {
            this.jTextField1.setText("Falsche Liste ausgewählt");
        }
    }//GEN-LAST:event_inwork_finished_rightActionPerformed

    private void inwork_finished_leftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inwork_finished_leftActionPerformed
        // Es wird der angeklickte Arbeitsschritt ermittelt
        int i = this.finishedList.getSelectedIndex();
        // Ist der Selectierte Index -1, wurde in einer falschen JList etwas angeklickt
        // dieser Fehler wird in dem Textfeld ausgegeben
        if (i != -1) {
            String s = (String) this.finishedmodel.getElementAt(i);
            String[] sf = s.split(",");
            sf[0] = sf[0].trim();
            // Die Process ID des Arbeitsschrittes wird geändert
            boolean b = dba.updateArbeitsschritt(p.getProjektid(), sf[0], 1);
            if (b) {
                this.jTextField1.setText("successful!");
            }
            // Die Model werden als erstes geleert und dann neu befüllt
            inworkmodel.clear();
            finishedmodel.clear();
            this.finishedlist = dba.getFinishedList(p.getProjektid());
            this.inworklist = dba.getInWorkList(this.p.getProjektid());
            inworkmodel = this.write(inworklist, inworkmodel);
            finishedmodel = this.write(finishedlist, finishedmodel);
        }
        else
        {
            this.jTextField1.setText("Falsche Liste ausgewählt");
        }
    }//GEN-LAST:event_inwork_finished_leftActionPerformed

    private void btnaddArbeitsschrittActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddArbeitsschrittActionPerformed
        // Es wird ein neuer Arbeitsschritt erstellt
        this.newarbeitsschrittdialog.setVisible(true);

        if (newarbeitsschrittdialog.isOk) {
            Arbeitsschritt a = newarbeitsschrittdialog.a;
            Mitarbeiter m = newarbeitsschrittdialog.getMitarbeiter();
            this.dba.insertArbeitsschritt(p, m, a);
        }
        // Es werden die Model geleert und neu befüllt
        inworkmodel.clear();
        todomodel.clear();
        finishedmodel.clear();
        todolist = dba.getToDoList(p.getProjektid());
        inworklist = dba.getInWorkList(p.getProjektid());
        finishedlist = dba.getFinishedList(p.getProjektid());
        todomodel = write(todolist, todomodel);
        inworkmodel = write(inworklist, inworkmodel);
        finishedmodel = write(finishedlist, finishedmodel);
    }//GEN-LAST:event_btnaddArbeitsschrittActionPerformed

    private void todoListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_todoListMouseClicked
        mouseclick(todoList, todomodel);
    }//GEN-LAST:event_todoListMouseClicked

    private void inworkListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inworkListMouseClicked
        mouseclick(inworkList, inworkmodel);
    }//GEN-LAST:event_inworkListMouseClicked

    private void finishedListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finishedListMouseClicked
        mouseclick(finishedList, finishedmodel);
    }//GEN-LAST:event_finishedListMouseClicked

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
            java.util.logging.Logger.getLogger(TaskboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaskboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaskboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaskboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new TaskboardGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddArbeitsschritt;
    private javax.swing.JList finishedList;
    private javax.swing.JList inworkList;
    private javax.swing.JButton inwork_finished_left;
    private javax.swing.JButton inwork_finished_right;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList todoList;
    private javax.swing.JButton todo_inwork_left;
    private javax.swing.JButton todo_inwork_right;
    // End of variables declaration//GEN-END:variables

    private DefaultListModel write(LinkedList<Arbeitsschritt> list, DefaultListModel m) {
        // Die Arbeitsschritte werden von der Liste ins Model geschrieben
        for (Arbeitsschritt a : list) {
            m.addElement(a.getArbeitsschrittid() + ", " + a.getBezeichnung());
        }
        return m;
    }

    private void mouseclick(JList list, DefaultListModel model) {
        // Wird ein Arbeitsschritt angeklickt soll im Textfeld eine Beschreibung
        // des Arbeitsschrittes und der zuständige Mitarbeiter angezeigt werden
        int i = list.getSelectedIndex();
        String s = (String) model.getElementAt(i);
        String[] sf = s.split(",");
        sf[0] = sf[0].trim();
        int arbeitsid = Integer.parseInt(sf[0]);
        String name = dba.getMitarbeiterFromArbeitsschritt(this.p.getProjektid(), arbeitsid);
        String text = dba.getBezeichnung(p.getProjektid(), arbeitsid);
        this.jTextField1.setText("Beschreibung: " + text + "    Zuständiger Mitarbeiter: " + name);
    }
}
