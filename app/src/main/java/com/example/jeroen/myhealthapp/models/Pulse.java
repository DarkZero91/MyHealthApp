package com.example.jeroen.myhealthapp.models;

import com.example.jeroen.myhealthapp.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jeroen on 16-11-2015.
 */
public class Pulse implements Measurement {
    private int id;
    @SerializedName("heartrate")
    private int heartRate;
    @SerializedName("created")
    private String timestamp = Date.getTimestamp();
    private boolean isSynchronized;

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }
    public int getHeartRate() { return heartRate; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getTimestamp() { return timestamp; }

    public boolean isSynchronized() { return isSynchronized; }
    public void setSynchronized(boolean isSynchronized) { this.isSynchronized = isSynchronized; }

    @Override
    public String toString() { return heartRate + " bpm"; }
}
