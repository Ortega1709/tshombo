package com.ortega.tshombo.models;

public class TelephoneModel {

    private String id;
    private String idMagasin;
    private String marque;
    private String nom;
    private String description;
    private double prix;

    public TelephoneModel(String id, String idMagasin, String marque, String nom, String description, double prix) {
        this.id = id;
        this.idMagasin = idMagasin;
        this.marque = marque;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public TelephoneModel(String idMagasin, String marque, String nom, String description, double prix) {
        this.idMagasin = idMagasin;
        this.marque = marque;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
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

    public double getPrix() {
        return prix;
    }


}
