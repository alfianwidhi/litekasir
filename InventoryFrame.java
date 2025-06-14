/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasilitekasir;

import dao.MenuDAO;
import model.Menu;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.KategoriItem;
import javax.swing.JComboBox;

/**
 *
 * @author ALFIAN WIDHI
 */
public class InventoryFrame extends javax.swing.JFrame {
    private MenuDAO menuDAO;
    private DefaultTableModel model;
    private Connection conn;
    
    public InventoryFrame(Connection conn) {
    this.conn = conn;
    menuDAO = new MenuDAO(conn);
    initComponents();      // Inisialisasi komponen GUI
    initTableModel();     // Setup model tabel
    loadData();           // Muat data menu   
    loadKategoriComboBox(); // Muat data kategori ke ComboBox
    setLocationRelativeTo(null); // Tengahkan window
    
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            populateFieldsFromSelectedRow();
        }
    });
    
    // Set close operation
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close(); // Tutup koneksi saat window ditutup
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public class KategoriItem {
        private int id;
        private String nama;

        public KategoriItem(int id, String nama) {
            this.id = id;
            this.nama = nama;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return nama;  // Ini yang tampil di JComboBox
        }
    }

    private void initTableModel() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("ID Kategori");
        model.addColumn("Nama Menu");
        model.addColumn("Harga");
        model.addColumn("Stok");
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
    }

    private void loadData() {
        try {
            model.setRowCount(0);
            List<Menu> daftarMenu = menuDAO.ambilSemuaMenu();
            for (Menu m : daftarMenu) {
                model.addRow(new Object[]{
                    m.getId(),
                    m.getIdKategori(),
                    m.getNama(),
                    m.getHarga(),
                    m.getStok()
                });
            }
        } catch (Exception ex) {
            showError("Gagal load data menu: " + ex.getMessage());
        }
    }
   
    private void tambahMenu() {
        String nama = jTextFieldNama.getText().trim();
        String hargaStr = jTextFieldHarga.getText().trim();
        String stokStr = jTextFieldStok.getText().trim();

        // Validasi input
        if (nama.isEmpty() || hargaStr.isEmpty() || stokStr.isEmpty() || jComboBoxKategori.getSelectedItem() == null) {
            showError("Semua field harus diisi!");
            return;
        }

        try {
            int harga = Integer.parseInt(hargaStr);
            int stok = Integer.parseInt(stokStr);

            // Ambil kategori yang dipilih
            Object selectedItem = jComboBoxKategori.getSelectedItem();
            if (!(selectedItem instanceof KategoriItem)) {
                showError("Kategori tidak valid! Silakan pilih ulang kategori.");
                return;
            }
            int idKategori = ((KategoriItem) selectedItem).getId();

            // Buat objek Menu dan simpan ke database
            Menu newMenu = new Menu(0, nama, harga, stok, idKategori);
            if (menuDAO.tambahMenu(newMenu)) {
                loadData();
                clearFields();
                showSuccess("Menu berhasil ditambahkan");
            } else {
                showError("Gagal menambahkan menu ke database.");
            }

        } catch (NumberFormatException e) {
            showError("Harga dan stok harus angka!");
        } catch (SQLException e) {
            showError("Gagal tambah menu: " + e.getMessage());
        }
    }

    private void updateMenu() {
        int row = jTable1.getSelectedRow();
        if (row < 0) {
            showWarning("Pilih menu yang akan diubah");
            return;
        }

        String nama = jTextFieldNama.getText().trim();
        String hargaStr = jTextFieldHarga.getText().trim();
        String stokStr = jTextFieldStok.getText().trim();

        if (nama.isEmpty() || hargaStr.isEmpty() || stokStr.isEmpty() || jComboBoxKategori.getSelectedItem() == null) {
            showError("Semua field harus diisi!");
            return;
        }

        try {
            int id = (int) model.getValueAt(row, 0);
            int harga = Integer.parseInt(hargaStr);
            int stok = Integer.parseInt(stokStr);
            int idKategori = ((KategoriItem) jComboBoxKategori.getSelectedItem()).getId();

            Menu updatedMenu = new Menu(id, nama, harga, stok, idKategori);
            if (menuDAO.ubahMenu(updatedMenu)) {
                loadData();
                clearFields();
                showSuccess("Menu berhasil diubah");
            }
        } catch (NumberFormatException e) {
            showError("Harga dan stok harus angka!");
        } catch (SQLException e) {
            showError("Gagal ubah menu: " + e.getMessage());
        }
    }

    private void hapusMenu() {
        int row = jTable1.getSelectedRow();
        if (row < 0) {
            showWarning("Pilih menu yang akan dihapus terlebih dahulu!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus menu ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            int idMenu = (int) model.getValueAt(row, 0);
            if (menuDAO.hapusMenu(idMenu)) {
                loadData();
                clearFields();
                showSuccess("Menu berhasil dihapus");
            }
        } catch (SQLException e) {
            showError("Gagal menghapus menu: " + e.getMessage());
        }
    }
    
    private void loadKategoriComboBox() {
    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/coffee_shop", "root", "")) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM kategori");
        jComboBoxKategori.removeAllItems();
        while (rs.next()) {
            jComboBoxKategori.addItem(new KategoriItem(rs.getInt("id"), rs.getString("nama")));
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal load kategori: " + e.getMessage());
    }
}

    private void clearFields() {
        jTextFieldNama.setText("");
        jTextFieldHarga.setText("");
        jTextFieldStok.setText("");
        jComboBoxKategori.setSelectedIndex(-1);
    }

    private void populateFieldsFromSelectedRow() {
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            jTextFieldNama.setText(model.getValueAt(row, 2).toString());
            jTextFieldHarga.setText(model.getValueAt(row, 3).toString());
            jTextFieldStok.setText(model.getValueAt(row, 4).toString());

            int idKategori = (int) model.getValueAt(row, 1);
            for (int i = 0; i < jComboBoxKategori.getItemCount(); i++) {
                if (((KategoriItem) jComboBoxKategori.getItemAt(i)).getId() == idKategori) {
                    jComboBoxKategori.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Peringatan", JOptionPane.WARNING_MESSAGE);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNama = new javax.swing.JTextField();
        jTextFieldHarga = new javax.swing.JTextField();
        jTextFieldStok = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnKembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxKategori = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Nama");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 181, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Harga");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 181, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("Stok");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 181, -1));

        jTextFieldNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        jTextFieldNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNamaActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 181, 30));

        jTextFieldHarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        jTextFieldHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHargaActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 181, 30));

        jTextFieldStok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        jTextFieldStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStokActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 181, 30));

        btnUpdate.setBackground(new java.awt.Color(0, 51, 204));
        btnUpdate.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel3.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, 34));

        btnDelete.setBackground(new java.awt.Color(0, 51, 204));
        btnDelete.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 90, 33));

        btnTambah.setBackground(new java.awt.Color(0, 51, 204));
        btnTambah.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel3.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, 34));

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        jTable1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 480, 420));

        btnKembali.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(0, 0, 153));
        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        jPanel3.add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("INVENTORY");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("ID Kategori");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 181, -1));

        jComboBoxKategori.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jComboBoxKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxKategoriActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBoxKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 180, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rm222-mind-31.jpg"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 470));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 700, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
       new DashboardFrame().setVisible(true);
            dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void jTextFieldNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNamaActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() == -1) {
        showWarning("Pilih menu yang akan diupdate terlebih dahulu!");
        return;
    }    
    // Memanggil method ubahMenu yang sudah dibuat
        updateMenu();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
         tambahMenu();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        // Memeriksa apakah ada baris yang dipilih di tabel
        if (jTable1.getSelectedRow() == -1) {
            showWarning("Pilih menu yang akan dihapus terlebih dahulu!");
            return;
        }    
    // Memanggil method hapusMenu yang sudah dibuat
        hapusMenu();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jTextFieldStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStokActionPerformed

    private void jTextFieldHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHargaActionPerformed

    private void jComboBoxKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxKategoriActionPerformed
        // TODO add your handling code here:
        KategoriItem selectedKategori = (KategoriItem) jComboBoxKategori.getSelectedItem(); // ✅ Benar
            int kategoriId = selectedKategori.getId(); // kalau ingin ID 
    }//GEN-LAST:event_jComboBoxKategoriActionPerformed

    /**
     * @param args the command line arguments
     */
      public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/coffee_shop", "root", "");
            new InventoryFrame(conn).setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal koneksi ke database: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<KategoriItem> jComboBoxKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldHarga;
    private javax.swing.JTextField jTextFieldNama;
    private javax.swing.JTextField jTextFieldStok;
    // End of variables declaration//GEN-END:variables
}
