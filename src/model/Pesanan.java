/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aplikasilitekasir.TransaksiFrame;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

/**
 *
 * @author ALFIAN WIDHI
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

