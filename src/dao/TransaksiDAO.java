/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import aplikasilitekasir.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Transaksi;

/**
 *
 * @author ALFIAN WIDHI
 */
public class TransaksiDAO {
    public static void simpanTransaksi(Transaksi transaksi) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO transaksi (nama_menu, harga, jumlah, total) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, transaksi.getNamaMenu());
            stmt.setInt(2, transaksi.getHarga());
            stmt.setInt(3, transaksi.getJumlah());
            stmt.setInt(4, transaksi.getTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

