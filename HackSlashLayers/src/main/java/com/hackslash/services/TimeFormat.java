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
        StringBuilder dateTime = new StringBuilder();

        dateTime.append("T");

        dateTime.append(CodeConstants.GMT_TIME.getValue());
        return dateTime.toString();
    }
}
