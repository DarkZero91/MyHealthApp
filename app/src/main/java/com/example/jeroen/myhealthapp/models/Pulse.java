package com.example.jeroen.myhealthapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 11-11-2015.
 */
public class Pulse implements Measurement {
    private List<Integer> data;

    public Pulse() {
        data = new ArrayList<Integer>();
    }

    public void addData(int data) {
        this.data.add(data);
    }

    public Integer[] getData() {
        Integer[] type = new Integer[data.size()];
        return data.toArray(type);
    }
}
