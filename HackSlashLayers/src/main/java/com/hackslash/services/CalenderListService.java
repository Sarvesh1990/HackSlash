package com.hackslash.services;

import com.hackslash.constants.Constants;
import com.hackslash.constants.RequestConstants;
import com.hackslash.constants.SpecialChars;
import com.hackslash.helper.UrlCreator;
import com.hackslash.utils.RequestCreator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class CalenderListService {

    private String calenderAuthToken;

    public CalenderListService(String calenderAuthToken) {
        this.calenderAuthToken = calenderAuthToken;
    }

    public String makeRequest() throws IOException, ParseException {
        RequestCreator requestCreator = new RequestCreator(calenderAuthToken);
        String output =  requestCreator.makeRequestForCalendar(Constants.CALENDAR_LIST_API, SpecialChars.BLANK_STRING.getValue(), RequestConstants.GET.getValue());
        return getCalendarId(output);
    }
    private String getCalendarId(String output) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(output);
        JSONArray items = (JSONArray) jsonObject.get("items");
        JSONObject item = (JSONObject) items.get(0);
        String calendarId = (String) item.get("id");
        return calendarId;
    }
}