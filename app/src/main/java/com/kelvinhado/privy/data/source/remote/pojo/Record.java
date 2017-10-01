package com.kelvinhado.privy.data.source.remote.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kelvin on 01/10/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Record {

    private String recordid;

    private Fields fields;

    public Record() {
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }
}
