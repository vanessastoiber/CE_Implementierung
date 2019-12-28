package com.project.rest_bestellung;

public class Produkt {

    private String griff;
    private String lenkertyp;
    private String material;
    private String schaltung;

    public Produkt(String griff, String lenkertyp, String material, String schaltung) {
        this.griff = griff;
        this.lenkertyp = lenkertyp;
        this.material = material;
        this.schaltung = schaltung;
    }

    public String getHandle() {
        return this.griff;
    }

    public void setHandle(String griff) {
        this.griff = griff;
    }

    public String getHandlebartype() {
        return this.lenkertyp;
    }

    public void setHandlebartype(String lenkertyp) {
        this.lenkertyp = lenkertyp;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSchaltung() {
        return this.schaltung;
    }

    public void setSchaltung(String schaltung) {
        this.schaltung = schaltung;
    }
}
