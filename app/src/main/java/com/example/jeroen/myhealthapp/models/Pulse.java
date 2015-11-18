package com.example.jeroen.myhealthapp.models;

import com.example.jeroen.myhealthapp.util.Date;

/**
 * Created by Jeroen on 16-11-2015.
 */
public class Pulse implements Measurement {
    private int id;
    private int heartRate;
    private String timestamp = Date.getTimestamp();

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }
    public int getHeartRate() { return heartRate; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() { return heartRate + " bpm"; }
}
