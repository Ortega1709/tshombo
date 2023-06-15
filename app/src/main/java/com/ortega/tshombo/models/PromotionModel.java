package com.ortega.tshombo.models;

public class PromotionModel {

    private String idMagasin;
    private String nom;
    private String promotion;

    public PromotionModel(String idMagasin, String nom, String promotion) {
        this.idMagasin = idMagasin;
        this.nom = nom;
        this.promotion = promotion;
    }

    public PromotionModel() {}

    public String getIdMagasin() {
        return idMagasin;
    }

    public String getNom() {
        return nom;
    }

    public String getPromotion() {
        return promotion;
    }
}
