package com.ptp.phamtanphat.quanlyhocsinh02008;

/**
 * Created by KhoaPhamPC on 4/10/2017.
 */

public class Hocsinh {
    private int Id;
    private String Hoten;
    private int Namsinh;
    private String Diachi;

    public Hocsinh(int id, String hoten, int namsinh, String diachi) {
        Id = id;
        Hoten = hoten;
        Namsinh = namsinh;
        Diachi = diachi;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public int getNamsinh() {
        return Namsinh;
    }

    public void setNamsinh(int namsinh) {
        Namsinh = namsinh;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }
}
