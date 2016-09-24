package com.hackslash.helper;

import com.hackslash.constants.Constants;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class UrlCreator {

    public static String getInsertEventUrl(String calendarId) {
        StringBuilder url = new StringBuilder();
        String baseUrl = "https://www.googleapis.com/calendar/v3/calendars";
        url.append(baseUrl);
        url.append("/" + calendarId);
        url.append("/" + "events");
        url.append("?" + "key=" + Constants.API_KEY);
        return url.toString();
    }

    public static String getUpdateEventUrl(String calendarId, String evtId) {
        StringBuilder url = new StringBuilder();
        String baseUrl = "https://www.googleapis.com/calendar/v3/calendars";
        url.append(baseUrl);
        url.append("/" + calendarId);
        url.append("/" + "events");
        url.append("/" + evtId);
        url.append("?" + "key=" + Constants.API_KEY);
        return url.toString();
    }

    public static String getDeleteEventUrl(String calendarId, String evtId) {
        return getUpdateEventUrl(calendarId, evtId);
    }

}
