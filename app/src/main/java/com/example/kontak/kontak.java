package com.example.kontak;

public class kontak {
    public kontak (String nama, String nohp){
        this.nama = nama;
        this.nohp = nohp;
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

    private String nama;
    private String nohp;
}
