package com.example.jakselian;

public class Kamus {
    Integer id;
    String kata;
    String arti;
    String kalimat;

    public Kamus(String kata, String arti, String kalimat) {
        this.kata = kata;
        this.arti = arti;
        this.kalimat = kalimat;
    }

    public Kamus(Integer id, String kata, String arti, String kalimat) {
        this.id = id;
        this.kata = kata;
        this.arti = arti;
        this.kalimat = kalimat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    public String getKalimat() {
        return kalimat;
    }

    public void setKalimat(String kalimat) {
        this.kalimat = kalimat;
    }
}
