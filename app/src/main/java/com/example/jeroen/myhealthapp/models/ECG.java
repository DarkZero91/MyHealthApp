package com.example.jeroen.myhealthapp.models;

import com.example.jeroen.myhealthapp.util.Date;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 11-11-2015.
 */
public class ECG implements Measurement {
    private int id;
    private List<Integer> data;
    @SerializedName("created")
    private String timestamp = Date.getTimestamp();
    private boolean isSynchronized;

    public ECG() { data = new ArrayList<Integer>(); }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void addData(int data) { this.data.add(data); }

    public void addData(int[] data) {
        for(int d : data) {
            this.data.add(d);
        }
    }

    public Integer[] getData() {
        Integer[] type = new Integer[data.size()];
        return data.toArray(type);
    }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getTimestamp() { return timestamp; }

    public boolean isSynchronized() { return isSynchronized; }
    public void setSynchronized(boolean isSynchronized) { this.isSynchronized = isSynchronized; }

    @Override
    public String toString() { return id + ""; }
}
