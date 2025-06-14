/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasilitekasir;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.print.PrinterException;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class HistoryFrame extends JFrame {
    private DefaultTableModel modelOrders;       // untuk jTable3 (tabel kiri)
    private DefaultTableModel modelOrderDetails; // untuk jTable1 (tabel kanan)

    public HistoryFrame() {
    initComponents();
    initTableModels();
}

    private void initTableModels() {
        // Model tabel kiri (orders)
        modelOrders = new DefaultTableModel(new String[]{"ID", "Tanggal", "Total", "Kembali"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        jTable3.setModel(modelOrders);
            // Atur lebar kolom dan nonaktifkan resizable untuk jTable3
            TableColumnModel columnModelOrders = jTable3.getColumnModel();
            // Kolom "ID"
            TableColumn idColumnOrders = columnModelOrders.getColumn(0);
            idColumnOrders.setPreferredWidth(50); // Lebar yang diinginkan
            idColumnOrders.setMinWidth(50);
            idColumnOrders.setMaxWidth(50);
            idColumnOrders.setResizable(false);
            // Kolom "Tanggal"
            TableColumn tanggalColumnOrders = columnModelOrders.getColumn(1);
            tanggalColumnOrders.setPreferredWidth(150);
            tanggalColumnOrders.setResizable(false);
            // Kolom "Total"
            TableColumn totalColumnOrders = columnModelOrders.getColumn(2);
            totalColumnOrders.setPreferredWidth(100);
            totalColumnOrders.setResizable(false);
            // Kolom "Kembali"
            TableColumn kembaliColumnOrders = columnModelOrders.getColumn(3);
            kembaliColumnOrders.setPreferredWidth(100);
            kembaliColumnOrders.setResizable(false);

        // Model tabel kanan (order details)
        modelOrderDetails = new DefaultTableModel(
            new String[]{"Menu", "Harga", "Qty", "Subtotal"}, 0
        ) {
    public boolean isCellEditable(int row, int col) { return false; } // Pastikan juga tidak bisa diedit
        };
        jTable1.setModel(modelOrderDetails);

            // Atur lebar kolom dan nonaktifkan resizable untuk jTable1
            TableColumnModel columnModelDetails = jTable1.getColumnModel();
            // Kolom "Menu"
            TableColumn menuColumnDetails = columnModelDetails.getColumn(0);
            menuColumnDetails.setPreferredWidth(150);
            menuColumnDetails.setResizable(false);
            // Kolom "Harga"
            TableColumn hargaColumnDetails = columnModelDetails.getColumn(1);
            hargaColumnDetails.setPreferredWidth(60);
            hargaColumnDetails.setMinWidth(60);
            hargaColumnDetails.setMaxWidth(60);
            hargaColumnDetails.setResizable(false);
            // Kolom "Qty"
            TableColumn qtyColumnDetails = columnModelDetails.getColumn(2);
            qtyColumnDetails.setPreferredWidth(40);
            qtyColumnDetails.setMinWidth(40);
            qtyColumnDetails.setMaxWidth(40);
            qtyColumnDetails.setResizable(false);
            // Kolom "Subtotal"
            TableColumn subtotalColumnDetails = columnModelDetails.getColumn(3);
            subtotalColumnDetails.setPreferredWidth(100);
            subtotalColumnDetails.setResizable(false);

        // Listener: klik baris di tabel kiri -> load detail di kanan
        jTable3.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable3.getSelectedRow() != -1) {
                int orderId = (int) jTable3.getValueAt(jTable3.getSelectedRow(), 0);
                loadOrderDetails(orderId);
            }
        });
        loadOrders();
    }
    
    // Load data orders ke tabel kiri (jTable3)
    private void loadOrders() {
        modelOrders.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee_shop", "root", "")) {
            String sql = "SELECT id, tanggal, total, bayar, kembali FROM orders ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelOrders.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("tanggal"),
                    rs.getInt("total"),
                    rs.getInt("bayar"),
                    rs.getInt("kembali")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data orders: " + ex.getMessage());
        }
    }

    // Load data order details ke tabel kanan (jTable1) berdasarkan orderId
    private void loadOrderDetails(int orderId) {
        DefaultTableModel modelOrderDetails = (DefaultTableModel) jTable1.getModel();
        modelOrderDetails.setRowCount(0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee_shop", "root", "")) {
            String sql = "SELECT nama_menu, harga, qty, subtotal FROM order_details WHERE order_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelOrderDetails.addRow(new Object[]{
                    rs.getString("nama_menu"),
                    rs.getInt("harga"),
                    rs.getInt("qty"),
                    rs.getInt("subtotal")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load detail: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 153));
        jButton4.setText("KEMBALI");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("HISTORY");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, -1));

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 270, 350));

        jTable3.setBackground(new java.awt.Color(204, 204, 204));
        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 370, 350));

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DETAIL TRANSAKSI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 270, 40));

        jPanel4.setBackground(new java.awt.Color(0, 0, 153));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("RIWAYAT TRANSAKSI");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel4)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 370, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rm222-mind-31.jpg"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 470));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new DashboardFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(HistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoryFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
