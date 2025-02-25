/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BB.java.DBConnectionSample;

/**
 *
 * @author samolyg
 */
public class Vásárok {
    private String nev;
    private String tipus;
    private String megye;
    private String telepules;
    private String irszam;
    private String cim;

    public Vásárok(String nev, String tipus, String megye, String telepules, String irszam, String cim) {
        this.nev = nev;
        this.tipus = tipus;
        this.megye = megye;
        this.telepules = telepules;
        this.irszam = irszam;
        this.cim = cim;
    }

    // Getterek és setterek

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getMegye() {
        return megye;
    }

    public void setMegye(String megye) {
        this.megye = megye;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }

    public String getIrszam() {
        return irszam;
    }

    public void setIrszam(String irszam) {
        this.irszam = irszam;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    @Override
    public String toString() {
        return "Vasarok{" +
                "nev='" + nev + '\'' +
                ", tipus='" + tipus + '\'' +
                ", megye='" + megye + '\'' +
                ", telepules='" + telepules + '\'' +
                ", irszam='" + irszam + '\'' +
                ", cim='" + cim + '\'' +
                '}';
    }
}
