package com.hackslash.helper;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class JSONCreator {

    public static String createInsertEventJSON(String startTime, String endTime, String evtDescription) {
        return "{\n" +
                " \"start\": {\n" +
                "  \"dateTime\": \"" + startTime +"\"\n" +
                " },\n" +
                " \"end\": {\n" +
                "  \"dateTime\": \"" + endTime + "\"\n" +
                " },\n" +
                " \"summary\": \""+ evtDescription + "\"\n" +
                "}";
    }

    public static String createUpdateEventJSON(String startTime, String endTime) {
        return "{\n" +
                " \"start\": {\n" +
                "  \"dateTime\": \"" + startTime +"\" \n" +
                " },\n" +
                " \"end\": {\n" +
                "  \"dateTime\": \"" + endTime + "\"\n" +
                " },\n" +
                "}";
    }
}
