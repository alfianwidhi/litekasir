/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Menu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KategoriItem;
/**
 *
 * @author ALFIAN WIDHI
 */
public class MenuDAO {
    private Connection conn;

    public MenuDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean tambahMenu(Menu menu) throws SQLException {
        String sql = "INSERT INTO menu(nama, id_kategori, harga, stok) VALUES(?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, menu.getNama());
        stmt.setInt(2, menu.getIdKategori());
        stmt.setInt(3, menu.getHarga());
        stmt.setInt(4, menu.getStok());
        return stmt.executeUpdate() > 0;
    }
}

    public List<KategoriItem> getAllKategori() throws SQLException {
        List<KategoriItem> list = new ArrayList<>();
        String sql = "SELECT * FROM kategori";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                list.add(new KategoriItem(id, nama));
            }
        }
        return list;
    }

    public List<Menu> ambilSemuaMenu() throws SQLException {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                int idKategori = rs.getInt("id_kategori");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                list.add(new Menu(id, nama, idKategori, harga, stok));
            }
        }
        return list;
    }

    public boolean ubahMenu(Menu menu) throws SQLException {
        String sql = "UPDATE menu SET nama = ?, id_kategori = ?, harga = ?, stok = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, menu.getNama());
        stmt.setInt(2, menu.getIdKategori());
        stmt.setInt(3, menu.getHarga());
        stmt.setInt(4, menu.getStok());
        stmt.setInt(5, menu.getId());
        int result = stmt.executeUpdate();
        stmt.close();
        return result > 0;
    }

    public boolean hapusMenu(int idMenu) throws SQLException {
        String sql = "DELETE FROM menu WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMenu);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean kurangiStok(int id, int jumlah) throws SQLException {
        String cekSql = "SELECT stok FROM menu WHERE id = ?";
        try (PreparedStatement cekStmt = conn.prepareStatement(cekSql)) {
            cekStmt.setInt(1, id);
            ResultSet rs = cekStmt.executeQuery();
            if (rs.next()) {
                int stokSekarang = rs.getInt("stok");
                if (stokSekarang < jumlah) {
                    return false;
                }
            } else {
                return false;
            }
        }

        String updateSql = "UPDATE menu SET stok = stok - ? WHERE id = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setInt(1, jumlah);
            updateStmt.setInt(2, id);
            return updateStmt.executeUpdate() > 0;
        }
    }

    public boolean cekIdKategori(int idKategori) throws SQLException {
        String sql = "SELECT COUNT(*) FROM kategori WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKategori);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
