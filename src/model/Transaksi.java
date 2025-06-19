/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ALFIAN WIDHI
 */
public class Transaksi {
    private String namaMenu;
    private int harga;
    private int jumlah;
    private int total;

    public Transaksi(String namaMenu, int harga, int jumlah) {
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.jumlah = jumlah;
        this.total = harga * jumlah;
    }

    public String getNamaMenu() { return namaMenu; }
    public int getHarga() { return harga; }
    public int getJumlah() { return jumlah; }
    public int getTotal() { return total; }
}
