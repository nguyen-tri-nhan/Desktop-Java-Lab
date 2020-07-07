/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import daos.StockDAO;
import dtos.StockDTO;

/**
 *
 * @author nguyentrinhand.dev
 */
public final class Main extends javax.swing.JFrame {

    
    private static int maxrow;
    private DefaultTableModel tblStocksModel = null;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);
        tblStocksModel = (DefaultTableModel) tblStocksView.getModel();
        firstController();
    }

    public void firstController() {
        StockDAO dao = new StockDAO();
        List<StockDTO> list = null;

        try {
            list = dao.loadAllRecord();
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No record found!");
            } else {
                tblStocksModel.setRowCount(0);
                for (StockDTO dto : list) {
                    tblStocksModel.addRow(dto.toVector());
                }
                maxrow = list.size() - 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    public void check() {
        if (tblStocksView.getSelectedRow() == 0) {
            jMovePrevious.setEnabled(false);
            jMoveFirst.setEnabled(false);
        } else {
            jMovePrevious.setEnabled(true);
            jMoveFirst.setEnabled(true);
        }
        if (tblStocksView.getSelectedRow() == maxrow) {
            jMoveNext.setEnabled(false);
            jMoveLast.setEnabled(false);
        } else {
            jMoveNext.setEnabled(true);
            jMoveLast.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStocksView = new javax.swing.JTable();
        jMoveFirst = new javax.swing.JButton();
        jMovePrevious = new javax.swing.JButton();
        jMoveNext = new javax.swing.JButton();
        jMoveLast = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblStocksView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "StockID", "StockName", "Address", "DateAvailable", "Note"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblStocksView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStocksViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStocksView);

        jMoveFirst.setText("<<");
        jMoveFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMoveFirstActionPerformed(evt);
            }
        });

        jMovePrevious.setText("<");
        jMovePrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMovePreviousActionPerformed(evt);
            }
        });

        jMoveNext.setText(">");
        jMoveNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMoveNextActionPerformed(evt);
            }
        });

        jMoveLast.setText(">>");
        jMoveLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMoveLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jMoveFirst)
                        .addGap(50, 50, 50)
                        .addComponent(jMovePrevious)
                        .addGap(50, 50, 50)
                        .addComponent(jMoveNext)
                        .addGap(50, 50, 50)
                        .addComponent(jMoveLast)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMoveFirst)
                    .addComponent(jMovePrevious)
                    .addComponent(jMoveNext)
                    .addComponent(jMoveLast))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMovePreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMovePreviousActionPerformed
        // TODO add your handling code here:
        int select = tblStocksView.getSelectedRow() - 1;
        tblStocksView.setRowSelectionInterval(select, select);
        check();
    }//GEN-LAST:event_jMovePreviousActionPerformed

    private void jMoveLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMoveLastActionPerformed
        // TODO add your handling code here:
        tblStocksView.setRowSelectionInterval(maxrow, maxrow);
        check();
    }//GEN-LAST:event_jMoveLastActionPerformed

    private void jMoveFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMoveFirstActionPerformed
        // TODO add your handling code here:
        tblStocksView.setRowSelectionInterval(0, 0);
        check();
    }//GEN-LAST:event_jMoveFirstActionPerformed

    private void jMoveNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMoveNextActionPerformed
        // TODO add your handling code here:
        int select = tblStocksView.getSelectedRow() + 1;
        tblStocksView.setRowSelectionInterval(select, select);
        check();
    }//GEN-LAST:event_jMoveNextActionPerformed

    private void tblStocksViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStocksViewMouseClicked
        // TODO add your handling code here:
        check();
    }//GEN-LAST:event_tblStocksViewMouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jMoveFirst;
    private javax.swing.JButton jMoveLast;
    private javax.swing.JButton jMoveNext;
    private javax.swing.JButton jMovePrevious;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStocksView;
    // End of variables declaration//GEN-END:variables

}
