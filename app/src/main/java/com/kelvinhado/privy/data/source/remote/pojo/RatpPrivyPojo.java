package com.kelvinhado.privy.data.source.remote.pojo;

import java.util.ArrayList;

/**
 * Created by kelvin on 30/09/2017.
 */

public class RatpPrivyPojo {
    private String nhits;
    private ArrayList<Record> records;

    public String getNhits() {
        return nhits;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public class Record {
        private String recordid;
        private String nom_voie;
        private String numero_voie;
        private String horaires_ouverture;
        private double[] geom_x_y;

        public String getRecordid() {
            return recordid;
        }

        public String getNom_voie() {
            return nom_voie;
        }

        public String getNumero_voie() {
            return numero_voie;
        }

        public String getHoraires_ouverture() {
            return horaires_ouverture;
        }

        public double[] getGeom_x_y() {
            return geom_x_y;
        }
    }
}