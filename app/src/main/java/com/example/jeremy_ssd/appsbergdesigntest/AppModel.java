package com.example.jeremy_ssd.appsbergdesigntest;

public class AppModel {

    private int color;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String pseudo;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    private String text;

    public AppModel(int color, String pseudo, String text) {
        this.color = color;
        this.pseudo = pseudo;
        this.text = text;
    }
}
