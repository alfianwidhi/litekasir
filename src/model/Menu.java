/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ALFIAN WIDHI
 */
public class Menu {
    private int id;
    private String nama;
    private int idKategori;
    private int harga;
    private int stok;

    public Menu(int id, String nama, int idKategori, int harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.idKategori = idKategori;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public int getIdKategori() { return idKategori; }
    public int getHarga() { return harga; }
    public int getStok() { return stok; }
}

