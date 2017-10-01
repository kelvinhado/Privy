package com.kelvinhado.privy.data.source.remote.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kelvin on 01/10/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
    private String nom_voie;
    private String numero_voie;
    private String horaires_ouverture;
    private double[] geom_x_y;

    public Fields() {
    }

    public String getNom_voie() {
        return nom_voie;
    }

    public void setNom_voie(String nom_voie) {
        this.nom_voie = nom_voie;
    }

    public String getNumero_voie() {
        return numero_voie;
    }

    public void setNumero_voie(String numero_voie) {
        this.numero_voie = numero_voie;
    }

    public String getHoraires_ouverture() {
        return horaires_ouverture;
    }

    public void setHoraires_ouverture(String horaires_ouverture) {
        this.horaires_ouverture = horaires_ouverture;
    }

    public double[] getGeom_x_y() {
        return geom_x_y;
    }

    public void setGeom_x_y(double[] geom_x_y) {
        this.geom_x_y = geom_x_y;
    }
}
