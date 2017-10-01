package com.kelvinhado.privy.data.source.remote.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by kelvin on 30/09/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatpPrivyPojo {
    private String nhits;
    private ArrayList<Record> records;


    public RatpPrivyPojo() {
    }

    public String getNhits() {
        return nhits;
    }

    public void setNhits(String nhits) {
        this.nhits = nhits;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }
}