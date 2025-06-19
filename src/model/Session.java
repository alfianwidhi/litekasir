/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ALFIAN WIDHI
 */
public class Session {
    private static String userName;
    private static String role;

    public static void setUserName(String name) {
        userName = name;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setRole(String role) {
        role = role;
    }

    public static String getRole() {
        return role;
    }
}

