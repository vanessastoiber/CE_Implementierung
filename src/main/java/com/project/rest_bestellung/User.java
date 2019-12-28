package com.project.rest_bestellung;

public class User {
    private Long id;
    private String email;
    private String vorname;
    private String nachname;
    private String username;
    private String lieferadresse;
    private String rechnungsadresse;

    public User(Long id, String email, String vorname, String nachname, String username, String lieferadresse, String rechnungsadresse) {
        this.id = id;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.username = username;
        this.lieferadresse = lieferadresse;
        this.rechnungsadresse = rechnungsadresse;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVorname() {
        return this.vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLieferadresse() {
        return this.lieferadresse;
    }

    public void setLieferadresse(String lieferadresse) {
        this.lieferadresse = lieferadresse;
    }

    public String getRechnungsadresse() {
        return this.rechnungsadresse;
    }

    public void setRechnungsadresse(String rechnungsadresse) {
        this.rechnungsadresse = rechnungsadresse;
    }
}
