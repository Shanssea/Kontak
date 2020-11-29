package com.example.kontak;

public class kontak {

    public kontak (String nama, String nohp, String alamat){
        this.nama = nama;
        this.nohp = nohp;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {

        this.nohp = nohp;
    }

    public String getAlamat(){ return alamat; }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    private String nama;
    private String nohp;
    private String alamat;
}
