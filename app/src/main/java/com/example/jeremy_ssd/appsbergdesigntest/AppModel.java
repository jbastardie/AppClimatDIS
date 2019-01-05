package com.example.jeremy_ssd.appsbergdesigntest;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class AppModel {


    private String temps;
    private String compteur;
    private String co2;
    private String nomApp;
    private Drawable logo;

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public String getCompteur() {
        return compteur;
    }

    public void setCompteur(String compteur) {
        this.compteur = compteur;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getNomApp() {
        return nomApp;
    }

    public void setNomApp(String nomApp) {
        this.nomApp = nomApp;
    }

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }

    public AppModel(String temps, String compteur, String co2, String nomApp, Drawable logo) {
        this.temps = temps;
        this.compteur = compteur;
        this.co2 = co2;
        this.nomApp=nomApp;
        this.logo=logo;
    }
}


