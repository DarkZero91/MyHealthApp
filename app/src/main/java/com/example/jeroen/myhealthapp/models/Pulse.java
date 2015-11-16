package com.example.jeroen.myhealthapp.models;

import com.example.jeroen.myhealthapp.util.Date;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 11-11-2015.
 */
public class Pulse implements Measurement {
    private int id;
    private List<Integer> data;
    private String timestamp = Date.getTimestamp();

    public Pulse() { data = new ArrayList<Integer>(); }

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

    @Override
    public String toString() {
        return "(" + id + ") " + timestamp;
    }
}
