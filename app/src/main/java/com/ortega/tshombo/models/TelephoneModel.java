package com.ortega.tshombo.models;

public class TelephoneModel {

    private String id;
    private String idMagasin;
    private String marque;
    private String nom;
    private String description;
    private String prix;
    private String photo;

    public TelephoneModel(String id, String idMagasin, String marque, String nom, String description, String prix, String photo) {
        this.id = id;
        this.idMagasin = idMagasin;
        this.marque = marque;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
    }

    public TelephoneModel(String idMagasin, String marque, String nom, String description, String prix, String photo) {
        this.idMagasin = idMagasin;
        this.marque = marque;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
    }

    public TelephoneModel() {

    }

    public String getId() {
        return id;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public String getMarque() {
        return marque;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getPrix() {
        return prix;
    }

    public String getPhoto() {
        return photo;
    }
}
