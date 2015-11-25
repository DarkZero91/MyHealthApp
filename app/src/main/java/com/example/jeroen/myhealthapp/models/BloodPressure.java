package com.example.jeroen.myhealthapp.models;

import com.example.jeroen.myhealthapp.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jeroen on 16-11-2015.
 */
public class BloodPressure implements Measurement {
    private int id;
    private int over;
    private int under;
    @SerializedName("created")
    private String timestamp = Date.getTimestamp();
    private boolean isSynchronized;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOver() {
        return over;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public int getUnder() {
        return under;
    }

    public void setUnder(int under) {
        this.under = under;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSynchronized() { return isSynchronized; }

    public void setSynchronized(boolean isSynchronized) { this.isSynchronized = isSynchronized; }

    @Override
    public String toString() { return over + "/" + under; }
}
