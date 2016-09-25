package com.hackslash.services;

import com.hackslash.constants.CodeConstants;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class TimeFormat {

    public static String getDateFormat(String startDate, String startTime){
        StringBuilder dateTime = new StringBuilder();
        switch(startDate) {
            case "today" : dateTime.append("");
                break;
            case "tomorrow" : dateTime.append("") ;
                break;
            default :
                if (!startDate.equals("")) {
                    dateTime.append(startDate);
                }
        }
        dateTime.append("T");
        dateTime.append(startTime);
        dateTime.append(CodeConstants.GMT_TIME.getValue());
        return dateTime.toString();
    }

    public static String getDateFormat(String startDate){
        StringBuilder dateTime = new StringBuilder();
        switch(startDate) {
            case "today" : dateTime.append("");
                break;
            case "tomorrow" : dateTime.append("") ;
                break;
            case "now" : dateTime.append("") ;
                break;
            default :
                if (!startDate.equals("")) {
                    dateTime.append(startDate);
                }
        }
        dateTime.append("T");
        dateTime.append("00:00:00");
        dateTime.append(CodeConstants.GMT_TIME.getValue());
        return dateTime.toString();
    }

    public static String getEndDateFormat(String startDate, String duration){
        String[] split = startDate.split("T");
        String[] time = split[1].split(":");
        StringBuilder dateTime = new StringBuilder(split[0]).append("T");
        dateTime.append(Integer.parseInt(time[0]) + Integer.parseInt(duration));
        for(int i = 1; i < time.length; i ++) {
            dateTime.append(":");
            dateTime.append(time[i]);
        }
        return dateTime.toString();
    }
}
