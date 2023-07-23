package com.ortega.tshombo.models;

public class MagasinModel {

    private String id;
    private String idUser;
    private String nom;
    private String adresse;
    private String map;
    private String numero;

    public MagasinModel(String id, String idUser, String nom, String adresse, String map, String numero) {
        this.id = id;
        this.idUser = idUser;
        this.nom = nom;
        this.adresse = adresse;
        this.map = map;
        this.numero = numero;
    }

    public MagasinModel(String idUser, String nom, String adresse, String map, String numero) {
        this.idUser = idUser;
        this.nom = nom;
        this.adresse = adresse;
        this.map = map;
        this.numero = numero;
    }

    public MagasinModel() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMap() {
        return map;
    }

    public String getNumero() {
        return numero;
    }
}
