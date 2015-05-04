/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.DefaultListModel;

/**
 *
 * @author Veronika
 */
public class TaskboardGUI extends javax.swing.JFrame {

    /**
     * 
     * Creates new form Taskboard
     */
    
    private DefaultListModel todomodel;
    private DefaultListModel inworkmodel;
    private DefaultListModel finishedmodel;
    
    public TaskboardGUI() {
        initComponents();
        todomodel = new DefaultListModel();
        this.todoList.setModel(todomodel);
        inworkmodel = new DefaultListModel();
        this.inworkList.setModel(inworkmodel);
        finishedmodel = new DefaultListModel();
        this.finishedList.setModel(finishedmodel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 5));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("TO DO"));

        todoList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(todoList);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

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

        getContentPane().add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("IN ARBEIT"));

        inworkList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(inworkList);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3);

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

        getContentPane().add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("FERTIG"));

        finishedList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(finishedList);

        jPanel5.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void todo_inwork_rightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todo_inwork_rightActionPerformed
        
    }//GEN-LAST:event_todo_inwork_rightActionPerformed

    private void todo_inwork_leftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todo_inwork_leftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_todo_inwork_leftActionPerformed

    private void inwork_finished_rightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inwork_finished_rightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inwork_finished_rightActionPerformed

    private void inwork_finished_leftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inwork_finished_leftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inwork_finished_leftActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaskboardGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList finishedList;
    private javax.swing.JList inworkList;
    private javax.swing.JButton inwork_finished_left;
    private javax.swing.JButton inwork_finished_right;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList todoList;
    private javax.swing.JButton todo_inwork_left;
    private javax.swing.JButton todo_inwork_right;
    // End of variables declaration//GEN-END:variables
}
