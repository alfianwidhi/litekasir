/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasilitekasir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import model.Pesanan;
import model.KategoriItem;
import model.Session;
import java.sql.Timestamp;
/**
 *
 * @author ALFIAN WIDHI
 */
public class TransaksiFrame extends javax.swing.JFrame {
    private java.util.List<Pesanan> keranjang = new ArrayList<>();
    private Map<Integer, JSpinner> spinnerMap = new HashMap<>();
    private Map<Integer, JCheckBox> checkBoxMap = new HashMap<>();
    private String lastStruk = "";
    private int lastOrderId = 0;
    private JPanel panelMenuList = new JPanel();

    /**
     * Creates new form TransaksiFrame_Kategori
     */
        public class Pesanan {
        private int idMenu;
        private String nama;
        private int harga;
        private int qty;

        public Pesanan(int idMenu, String nama, int harga, int qty) {
            this.idMenu = idMenu;
            this.nama = nama;
            this.harga = harga;
            this.qty = qty;
        }
        public int getIdMenu() { return idMenu; }
        public String getNama() { return nama; }
        public int getHarga() { return harga; }
        public int getQty() { return qty; }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }
    
    public TransaksiFrame() {
        initComponents();

        panelMenuList.setLayout(new BoxLayout(panelMenuList, BoxLayout.Y_AXIS));
        panelMenuList.setBackground(new Color(200, 200, 200)); // warna netral
        jScrollPane1.setViewportView(panelMenuList);
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // hanya vertikal

        loadKategoriComboBox();

        kategoriComboBox.addActionListener(e -> {
            String kategori = (String) kategoriComboBox.getSelectedItem();
            if (kategori != null) {
                loadMenuByKategori(kategori);
            }
        });

        if (kategoriComboBox.getItemCount() > 0) {
            kategoriComboBox.setSelectedIndex(0);
            String kategori = (String) kategoriComboBox.getSelectedItem();
            loadMenuByKategori(kategori);
        }
    }

    public static String centerText(String text, int width) {
        if (text == null || width <= text.length()) {
            return text;
        }
        int padding = (width - text.length()) / 2;
        return String.format("%" + (padding + text.length()) + "s", text);
    }

    private void loadKategoriComboBox() {
        kategoriComboBox.removeAllItems();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/litekasir", "root", "")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nama FROM kategori ORDER BY id ASC");
            while (rs.next()) {
                kategoriComboBox.addItem(rs.getString("nama"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load kategori: " + e.getMessage());
        }
    }

    private void loadMenuByKategori(String kategori) {
        panelMenuList.removeAll();
        spinnerMap.clear();
        checkBoxMap.clear();

        Font fontMenu = new Font("Segoe UI", Font.BOLD, 12); 
        Color warnaFont = new Color(33, 33, 33); 
        Color warnaBaris = new Color(255, 255, 255); 

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/litekasir", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT m.id, m.nama, m.harga, m.stok FROM menu m JOIN kategori k ON m.id_kategori = k.id WHERE k.nama = ?");
            stmt.setString(1, kategori);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idMenu = rs.getInt("id");
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");

                JPanel row = new JPanel();
                row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
                row.setBackground(warnaBaris);
                row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 

                JLabel lblNama = new JLabel(nama);
                lblNama.setFont(fontMenu);
                lblNama.setForeground(warnaFont);
                lblNama.setPreferredSize(new Dimension(110, 28));

                JLabel lblHarga = new JLabel("Rp. " + harga);
                lblHarga.setFont(fontMenu);
                lblHarga.setForeground(warnaFont);
                lblHarga.setPreferredSize(new Dimension(80, 30));

                JLabel lblStok = new JLabel("Stok: " + stok);
                lblStok.setFont(fontMenu);
                lblStok.setForeground(warnaFont);
                lblStok.setPreferredSize(new Dimension(60, 30));

                JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, stok, 1));
                spinner.setFont(fontMenu);
                spinner.setPreferredSize(new Dimension(45, 28));

                JCheckBox checkBox = new JCheckBox();
                checkBox.setBackground(warnaBaris);

                row.add(Box.createRigidArea(new Dimension(8, 0)));
                row.add(lblNama);
                row.add(Box.createRigidArea(new Dimension(8, 0)));
                row.add(lblHarga);
                row.add(Box.createRigidArea(new Dimension(8, 0)));
                row.add(lblStok);
                row.add(Box.createRigidArea(new Dimension(8, 0)));
                row.add(spinner);
                row.add(Box.createRigidArea(new Dimension(8, 0)));
                row.add(checkBox);
                row.add(Box.createHorizontalGlue());

                spinnerMap.put(idMenu, spinner);
                checkBoxMap.put(idMenu, checkBox);

                panelMenuList.add(row);
                panelMenuList.add(Box.createVerticalStrut(2)); 
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load menu: " + e.getMessage());
        }
        panelMenuList.revalidate();
        panelMenuList.repaint();
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
        JSpinner = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        btnTambah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtTotalPesanan = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaOrder = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        kategoriComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JSpinner.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane1.setHorizontalScrollBar(null);
        JSpinner.setViewportView(jScrollPane1);

        jPanel1.add(JSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 380, 360));

        btnTambah.setBackground(new java.awt.Color(0, 51, 204));
        btnTambah.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, 130, -1));

        btnBatal.setBackground(new java.awt.Color(0, 51, 204));
        btnBatal.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 129, -1));

        btnBayar.setBackground(new java.awt.Color(0, 51, 204));
        btnBayar.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setText("BAYAR");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, 130, -1));

        btnCetak.setBackground(new java.awt.Color(0, 51, 204));
        btnCetak.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setText("CETAK");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jPanel1.add(btnCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, 129, -1));

        jLabel33.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        jLabel33.setText("BAYAR");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 50, -1));

        jLabel32.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        jLabel32.setText("TOTAL");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, -1, -1));

        txtTotalPesanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        txtTotalPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPesananActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotalPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 209, -1));

        txtBayar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });
        jPanel1.add(txtBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 209, -1));

        textAreaOrder.setColumns(20);
        textAreaOrder.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        textAreaOrder.setRows(5);
        textAreaOrder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 3));
        jScrollPane3.setViewportView(textAreaOrder);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 271, 280));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("TRANSAKSI");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jButton4.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 153));
        jButton4.setText("KEMBALI");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 100, 30));

        kategoriComboBox.setBackground(new java.awt.Color(0, 51, 153));
        kategoriComboBox.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        kategoriComboBox.setForeground(new java.awt.Color(255, 255, 255));
        kategoriComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KOPI", "NON-KOPI", "MAKANAN", "SNACK" }));
        kategoriComboBox.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        kategoriComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(kategoriComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 378, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rm222-mind-31.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -30, 710, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:                                        
        int total = 0;
        for (Map.Entry<Integer, JCheckBox> entry : checkBoxMap.entrySet()) {
            int idMenu = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            if (checkBox.isSelected()) {
                int qty = (int) spinnerMap.get(idMenu).getValue();
                if (qty > 0) {
                    boolean sudahAda = false;
                    for (Pesanan p : keranjang) {
                        if (p.getIdMenu() == idMenu) {
                            p.setQty(p.getQty() + qty); 
                            sudahAda = true;
                            break;
                        }
                    }
                    if (!sudahAda) {
                        try (Connection conn = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/litekasir", "root", "")) {
                            PreparedStatement stmt = conn.prepareStatement(
                                    "SELECT nama, harga FROM menu WHERE id = ?");
                            stmt.setInt(1, idMenu);
                            ResultSet rs = stmt.executeQuery();
                            if (rs.next()) {
                                String nama = rs.getString("nama");
                                int harga = rs.getInt("harga");
                                Pesanan pesanan = new Pesanan(idMenu, nama, harga, qty);
                                keranjang.add(pesanan);
                            }
                            rs.close();
                            stmt.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Gagal ambil menu: " + e.getMessage());
                        }
                    }
                }
            }
        }
        textAreaOrder.setText("");
        for (Pesanan p : keranjang) {
            int subtotal = p.getHarga() * p.getQty();
            total += subtotal;
            textAreaOrder.append(p.getNama() + " x" + p.getQty() + " = Rp. " + subtotal + "\n");
        }
        txtTotalPesanan.setText(String.valueOf(total));
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        keranjang.clear();
        textAreaOrder.setText("");
        txtTotalPesanan.setText("");
        txtBayar.setText("");
        for (JSpinner spinner : spinnerMap.values()) {
            spinner.setValue(0);
        }
        for (JCheckBox checkBox : checkBoxMap.values()) {
            checkBox.setSelected(false);
        }
        lastStruk = "";
        lastOrderId = 0;
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:                                                         
            if (keranjang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Keranjang kosong!");
                return;
            }
            int total = 0;
            for (Pesanan p : keranjang) {
                total += p.getHarga() * p.getQty();
            }
            int bayar;
            try {
                bayar = Integer.parseInt(txtBayar.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nominal bayar tidak valid!");
                return;
            }
            if (bayar < total) {
                JOptionPane.showMessageDialog(this, "Uang bayar kurang!");
                return;
            }
            int kembalian = bayar - total;
            java.sql.Timestamp waktuSekarang = new java.sql.Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String tanggalJam = sdf.format(waktuSekarang);

            textAreaOrder.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));

            StringBuilder struk = new StringBuilder();
            String namaToko = "LITE KASIR";
            String alamatToko = "Jl. Ahmad Yani No.02, Surabaya";
            int lebarStruk = 32;

            struk.append(centerText(namaToko, lebarStruk)).append("\n");
            struk.append(centerText(alamatToko, lebarStruk)).append("\n");
            struk.append("--------------------------------\n");
            struk.append("Tanggal : ").append(tanggalJam).append("\n\n");
            struk.append(String.format("%-15s %4s %12s\n", "Pesanan", "Jml", "Subtotal"));
            struk.append("--------------------------------\n");
            for (Pesanan p : keranjang) {
                int subtotal = p.getHarga() * p.getQty();
                struk.append(String.format("%-15s %4d %12s\n", p.getNama(), p.getQty(), "Rp. " + subtotal));
            }
            struk.append("--------------------------------\n");
            struk.append(String.format("%-15s %16s\n", "Total", "Rp. " + total));
            struk.append(String.format("%-15s %16s\n", "Bayar", "Rp. " + bayar));
            struk.append(String.format("%-15s %16s\n", "Kembali", "Rp. " + kembalian));
            struk.append("--------------------------------\n");
            struk.append("\n");
            struk.append(centerText("======= TERIMA KASIH =======", lebarStruk)).append("\n");
            struk.append(centerText("---- www.LiteKasir.com ----", lebarStruk)).append("\n");

            textAreaOrder.setText(struk.toString());
            lastStruk = struk.toString();

            try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/litekasir", "root", "")) {

            String sqlOrder = "INSERT INTO orders (tanggal, total, bayar, kembali) VALUES (?, ?, ?, ?)";
            PreparedStatement psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setTimestamp(1, waktuSekarang);
            psOrder.setInt(2, total);
            psOrder.setInt(3, bayar);
            psOrder.setInt(4, kembalian);
            psOrder.executeUpdate();
            ResultSet generatedKeys = psOrder.getGeneratedKeys();
            int idOrder = 0;
            if (generatedKeys.next()) {
                idOrder = generatedKeys.getInt(1);
            }
            psOrder.close();

            String sqlDetail = "INSERT INTO order_details (order_id, menu_id, nama_menu, qty, harga, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
            for (Pesanan p : keranjang) {
                psDetail.setInt(1, idOrder);
                psDetail.setInt(2, p.getIdMenu());
                psDetail.setString(3, p.getNama());
                psDetail.setInt(4, p.getQty());
                psDetail.setInt(5, p.getHarga());
                psDetail.setInt(6, p.getHarga() * p.getQty());
                psDetail.addBatch();
            }
            psDetail.executeBatch();
            psDetail.close();

                    String sqlUpdateStok = "UPDATE menu SET stok = stok - ? WHERE id = ?";
                    PreparedStatement psUpdateStok = conn.prepareStatement(sqlUpdateStok);
                    for (Pesanan p : keranjang) {
                        psUpdateStok.setInt(1, p.getQty());
                        psUpdateStok.setInt(2, p.getIdMenu());
                        psUpdateStok.addBatch();
                    }
                    psUpdateStok.executeBatch();
                    psUpdateStok.close();

                    JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan dan stok diperbarui!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal simpan transaksi: " + e.getMessage());
            }
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:                                                                               
        try {
            boolean complete = textAreaOrder.print();
            if (complete) {
                JOptionPane.showMessageDialog(this, "Struk berhasil dicetak!");
            } else {
                JOptionPane.showMessageDialog(this, "Print dibatalkan.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal print: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void txtTotalPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPesananActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBayarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new DashboardFrame().setVisible(true);
            dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void kategoriComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriComboBoxActionPerformed
        // TODO add your handling code here:
        String kategori = (String) ((JComboBox)evt.getSource()).getSelectedItem();
        loadMenuByKategori(kategori);
    }//GEN-LAST:event_kategoriComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSpinner;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> kategoriComboBox;
    private javax.swing.JTextArea textAreaOrder;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtTotalPesanan;
    // End of variables declaration//GEN-END:variables
    }
