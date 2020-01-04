package com.taufiq.ayger.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SemuaLapangan {
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("namalap")
    private String namalap;
    @SerializedName("desc")
    private String desc;
    @SerializedName("namakategori")
    private String namakategori;
    @SerializedName("nama")
    private String nama;
    @SerializedName("harga")
    private String harga;
    @SerializedName("idpenyewa")
    private String idpenyewa;
    @SerializedName("idalat")
    private String idalat;
    @SerializedName("saldo")
    private  String saldo;

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getIdalat() {
        return idalat;
    }

    public void setIdalat(String idalat) {
        this.idalat = idalat;
    }

    public String getIdpenyewa() {
        return idpenyewa;
    }

    public void setIdpenyewa(String idpenyewa) {
        this.idpenyewa = idpenyewa;
    }

    public SemuaLapangan(String saldo,String alamat, String namalap, String desc, String namakategori, String nama, String harga, String idpenyewa,String idalat) {
        this.saldo=saldo;
        this.alamat = alamat;
        this.namalap = namalap;
        this.desc = desc;
        this.namakategori = namakategori;
        this.nama = nama;
        this.harga = harga;
        this.idpenyewa=idpenyewa;
        this.idalat=idalat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamalap() {
        return namalap;
    }

    public void setNamalap(String namalap) {
        this.namalap = namalap;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNamakategori() {
        return namakategori;
    }

    public void setNamakategori(String namakategori) {
        this.namakategori = namakategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }


    @Override
    public String toString() {
        return
                "semualapangan{" +
                        "alamat = '" + alamat + '\'' +
                        ",namalap = '" + namalap + '\'' +
                        ",desc = '" + desc + '\'' +
                        ",harga = '" + harga + '\'' +
                        ",namakategori = '" + namakategori + '\'' +
                        ",nama = '" + nama + '\'' +

                        "}";
    }
}
