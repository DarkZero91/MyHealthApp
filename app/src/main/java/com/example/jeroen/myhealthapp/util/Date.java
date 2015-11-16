package com.example.jeroen.myhealthapp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jeroen on 16-11-2015.
 */
public class Date {
    private Date() {}

    public static String getTimestamp() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy H:m");
        return format.format(cal.getTime());
    }
}
