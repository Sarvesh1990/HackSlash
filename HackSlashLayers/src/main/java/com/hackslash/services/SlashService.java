package com.hackslash.services;

import com.hackslash.constants.RequestConstants;
import com.hackslash.constants.SpecialChars;
import com.hackslash.helper.Auth2;
import com.hackslash.helper.JSONCreator;
import com.hackslash.helper.UrlCreator;
import com.hackslash.utils.RequestCreator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static com.hackslash.utils.ValidationUtil.isStringSet;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class SlashService {
    private Map<String, String> requestMap;
    private HttpServletResponse response;
    private String userId;
    private String authToken;

    public SlashService(Map<String, String> requestMap, HttpServletResponse response) {
        this.requestMap = requestMap;
        this.response = response;
        this.userId = requestMap.get("userId");
        this.authToken = Auth2.getAuthToken(this.userId);
    }

    public void processRequest() throws IOException {
        String messageRecipient = requestMap.get(RequestConstants.CHAT.getValue());
        if(isStringSet(messageRecipient)) {
            String[] messageRecipientList = messageRecipient.split(SpecialChars.COLON.getValue());
            if(messageRecipientList[0].equals("g")) {
                FlockService flockService  = new FlockService();
                flockService.getUserListFromGroup(messageRecipient, requestMap);
            }
        } else {
            System.out.println("Error with received slash command");
        }
        String inputPattern = requestMap.get(RequestConstants.TEXT.getValue());
        parsePattern(inputPattern);
    }

    private void parsePattern(String inputPattern) {
        ArrayList<String> parsedList = (ArrayList) Arrays.asList(inputPattern.split(" "));
        if (parsedList.size() <= 1) {
            return;
        }
        String method = parsedList.get(0);
        switch (method) {
            case "insert" :
                if (parsedList.size() == 5) {
                    insertEvent(parsedList.get(1), parsedList.get(2), parsedList.get(3), parsedList.get(4));
                } else if (parsedList.size() == 4) {
                    insertEvent(parsedList.get(1), parsedList.get(2), parsedList.get(3));
                }
//                String url = UrlCreator.getInsertEventUrl();
                RequestCreator requestCreator = new RequestCreator(this.authToken);
                break;
            case "update" :
                if (parsedList.size() == 5) {
                    updateEvent(parsedList.get(1), parsedList.get(2), parsedList.get(3), parsedList.get(4));
                } else if (parsedList.size() == 4) {
                    updateEvent(parsedList.get(1), parsedList.get(2),  parsedList.get(3));
                }
                break;
            case "delete" : deleteEvent(parsedList.get(1));
                break;
            default: ;
        }
    }

    private void insertEvent(String startDate, String startTime, String duration, String evtDescription) {
        String parsedStartDate = TimeFormat.getDateFormat(startDate, startTime);
        String parsedEndDate = TimeFormat.getEndDateFormat(parsedStartDate, duration);
        String jsonString = JSONCreator.createInsertEventJSON(parsedStartDate, parsedEndDate, evtDescription);

    }

    private void insertEvent(String startDate, String duration, String evtDescription) {
        String parsedStartDate = TimeFormat.getDateFormat(startDate);
        String parsedEndDate =  TimeFormat.getEndDateFormat(parsedStartDate, duration);
        String jsonString = JSONCreator.createInsertEventJSON(parsedStartDate, parsedEndDate, evtDescription);
    }

    private String updateEvent(String evtId, String startDate, String startTime, String duration) {
        StringBuilder queryString = new StringBuilder();
        String parsedStartDate = TimeFormat.getDateFormat(startDate, startTime);
        queryString.append(parsedStartDate);
        queryString.append(TimeFormat.getEndDateFormat(parsedStartDate, duration));
        return queryString.toString();
    }

    private String updateEvent(String evtId, String startDate, String duration) {
        StringBuilder queryString = new StringBuilder();
        String parsedStartDate = TimeFormat.getDateFormat(startDate);
        queryString.append(parsedStartDate);
        queryString.append(TimeFormat.getEndDateFormat(parsedStartDate, duration));
        return queryString.toString();
    }

    private void deleteEvent(String evtId) {
        StringBuilder queryString = new StringBuilder();
    }
}
