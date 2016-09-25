package com.hackslash.services;

import com.hackslash.constants.Constants;
import com.hackslash.constants.RequestConstants;
import com.hackslash.constants.SpecialChars;
import com.hackslash.helper.JSONCreator;
import com.hackslash.helper.UrlCreator;
import com.hackslash.pojos.UserTokenDetails;
import com.hackslash.utils.RequestCreator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.hackslash.utils.ValidationUtil.isStringSet;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class CalenderListService {

    public String makeRequest(String calenderAuthToken, String userId) throws Exception {
        RequestCreator requestCreator = new RequestCreator(calenderAuthToken);
        String output =  requestCreator.makeRequestForCalendar(Constants.CALENDAR_LIST_API, SpecialChars.BLANK_STRING.getValue(), RequestConstants.GET.getValue(), userId);
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

    public void setCalendarEventForUser(String inputPattern, String userId) throws Exception {
        String authToken = Constants.USER_TOKEN_MAP.get(userId).getCalendarApiAuthToken();
        String calendarId = Constants.USER_TOKEN_MAP.get(userId).getCalendarId() == null ? makeRequest(authToken, userId) : Constants.USER_TOKEN_MAP.get(userId).getCalendarId();
        System.out.println("Calendar id for user id : " + userId + " : " + calendarId);
        Constants.USER_TOKEN_MAP.get(userId).setCalendarId(calendarId);
        parsePattern(inputPattern, authToken, userId);
    }

    public void setCalendarEventUserList (String inputPattern, ArrayList<Map<String, String>> userList) throws Exception {
        for(Map<String, String> user : userList) {
            String userId = user.get(RequestConstants.ID.getValue());
            if(Constants.USER_TOKEN_MAP.get(userId) != null && isStringSet(Constants.USER_TOKEN_MAP.get(userId).getCalendarApiAuthToken())) {
                setCalendarEventForUser(inputPattern, userId);
            }
        }
    }

    private void parsePattern(String inputPattern, String authToken, String userId) throws Exception {
        List<String> parsedList = Arrays.asList(inputPattern.split(" "));
        if (parsedList.size() <= 1) {
            return;
        }
        String method = parsedList.get(0);
        String params = "", url;
        RequestCreator requestCreator = new RequestCreator(authToken);
        String receiverCalenderId = Constants.USER_TOKEN_MAP.get(userId).getCalendarId();
        switch (method) {
            case "insert" :
                if (parsedList.size() == 5) {
                    params = insertEvent(parsedList.get(1), parsedList.get(2), parsedList.get(3), parsedList.get(4));
                } else if (parsedList.size() == 4) {
                    params = insertEvent(parsedList.get(1), parsedList.get(2), parsedList.get(3));
                }
                url = UrlCreator.getInsertEventUrl(receiverCalenderId);
                requestCreator.makeRequestForCalendar(url, params, RequestConstants.POST.getValue(), userId);
                break;
            case "update" :
                if (parsedList.size() == 5) {
                    params =  updateEvent(parsedList.get(2), parsedList.get(3), parsedList.get(4));
                } else if (parsedList.size() == 4) {
                    params = updateEvent(parsedList.get(2), parsedList.get(3));
                }
                url = UrlCreator.getUpdateEventUrl(receiverCalenderId, parsedList.get(1));
                requestCreator.makeRequestForCalendar(url, params, RequestConstants.PUT.getValue(), userId);
                break;
            case "delete" :
                url = UrlCreator.getDeleteEventUrl(receiverCalenderId, parsedList.get(1));
                requestCreator.makeRequestForCalendar(url, params, RequestConstants.DELETE.getValue(), userId);
                break;
            default: ;
        }
    }

    private String insertEvent(String startDate, String startTime, String duration, String evtDescription) {
        String parsedStartDate = TimeFormat.getDateFormat(startDate, startTime);
        String parsedEndDate = TimeFormat.getEndDateFormat(parsedStartDate, duration);
        String jsonString = JSONCreator.createInsertEventJSON(parsedStartDate, parsedEndDate, evtDescription);
        return jsonString;
    }

    private String insertEvent(String startDate, String duration, String evtDescription) {
        String parsedStartDate = TimeFormat.getDateFormat(startDate);
        String parsedEndDate =  TimeFormat.getEndDateFormat(parsedStartDate, duration);
        String jsonString = JSONCreator.createInsertEventJSON(parsedStartDate, parsedEndDate, evtDescription);
        return jsonString;
    }

    private String updateEvent(String startDate, String startTime, String duration) {
        String parsedStartDate = TimeFormat.getDateFormat(startDate, startTime);
        String parsedEndDate = TimeFormat.getEndDateFormat(parsedStartDate, duration);
        String jsonString = JSONCreator.createUpdateEventJSON(parsedStartDate, parsedEndDate);
        return jsonString;
    }

    private String updateEvent(String startDate, String duration) {
        String parsedStartDate = TimeFormat.getDateFormat(startDate);
        String parsedEndDate = TimeFormat.getEndDateFormat(parsedStartDate, duration);
        String jsonString = JSONCreator.createUpdateEventJSON(parsedStartDate, parsedEndDate);
        return jsonString;
    }
}
