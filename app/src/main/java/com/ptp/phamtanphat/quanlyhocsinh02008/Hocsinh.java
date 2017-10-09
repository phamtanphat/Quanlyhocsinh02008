package com.ptp.phamtanphat.quanlyhocsinh02008;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KhoaPhamPC on 4/10/2017.
 */

public class Hocsinh implements Parcelable{
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

    protected Hocsinh(Parcel in) {
        Id = in.readInt();
        Hoten = in.readString();
        Namsinh = in.readInt();
        Diachi = in.readString();
    }

    public static final Creator<Hocsinh> CREATOR = new Creator<Hocsinh>() {
        @Override
        public Hocsinh createFromParcel(Parcel in) {
            return new Hocsinh(in);
        }

        @Override
        public Hocsinh[] newArray(int size) {
            return new Hocsinh[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(Hoten);
        parcel.writeInt(Namsinh);
        parcel.writeString(Diachi);
    }
}
