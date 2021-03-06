package com.hackslash.services;

import com.hackslash.constants.Constants;
import com.hackslash.constants.MessageTypes;
import com.hackslash.constants.RequestConstants;
import com.hackslash.constants.SpecialChars;
import com.hackslash.helper.FlockAPIUserToken;
import com.hackslash.helper.JSONCreator;
import com.hackslash.helper.UrlCreator;
import com.hackslash.utils.RequestCreator;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.hackslash.utils.ValidationUtil.isStringSet;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class SlashService {
    private Map<String, String> requestMap;
    private HttpServletResponse response;
    private String senderUserId;
    private String senderAuthToken;
    private String receiverCalenderId;

    public SlashService(Map<String, String> requestMap, HttpServletResponse response) {
        this.requestMap = requestMap;
        this.response = response;
        senderUserId = requestMap.get(RequestConstants.USER_ID.getValue());
        senderAuthToken = FlockAPIUserToken.getAuthToken(senderUserId);
    }

    public void processRequest() throws Exception {
        String messageRecipient = requestMap.get(RequestConstants.CHAT.getValue());
        String inputPattern = requestMap.get(RequestConstants.TEXT.getValue());
        if(isStringSet(messageRecipient)) {
            String[] messageRecipientList = messageRecipient.split(SpecialChars.COLON.getValue());
            FlockService flockService  = new FlockService();
            CalenderListService calendarListService = new CalenderListService();
            if(messageRecipientList[0].equals("g")) {
                if(Constants.USER_TOKEN_MAP.get(requestMap.get(RequestConstants.USER_ID.getValue())) != null) {
                    ArrayList<Map<String, String>> userList = flockService.getUserListFromGroup(messageRecipient, Constants.USER_TOKEN_MAP.get(requestMap.get(RequestConstants.USER_ID.getValue())).getFlockToken());
                    if (userList != null) {
                        flockService.sendMessageToUserList(MessageTypes.MESSAGE_ADD_MEETING.getValue(), userList, senderAuthToken);
                        calendarListService.setCalendarEventUserList(inputPattern, userList);
                    }
                } else {
                    System.out.println("User has not installed the app. How can he send message?");
                }
            } else {
                flockService.sendMessageToUser(MessageTypes.MESSAGE_ADD_MEETING.getValue(), requestMap.get(RequestConstants.CHAT.getValue()), Constants.USER_TOKEN_MAP.get(requestMap.get(RequestConstants.USER_ID.getValue())).getFlockToken(),
                        Constants.USER_TOKEN_MAP.containsKey(requestMap.get(RequestConstants.CHAT.getValue())));
                calendarListService.setCalendarEventForUser(inputPattern, requestMap.get(RequestConstants.CHAT.getValue()));
            }
        } else {
            System.out.println("Error with received slash command");
        }
        String inputPattern = requestMap.get(RequestConstants.TEXT.getValue());
        parsePattern(inputPattern);
    }

    private void parsePattern(String inputPattern) throws IOException {
        List<String> parsedList =  Arrays.asList(inputPattern.split(" "));
        if (parsedList.size() <= 1) {
            return;
        }
        String method = parsedList.get(0);
        String params = "", url;
        RequestCreator requestCreator = new RequestCreator(senderAuthToken);
        switch (method) {
            case "insert" :
                if (parsedList.size() == 5) {
                    params = insertEvent(parsedList.get(1), parsedList.get(2), parsedList.get(3), parsedList.get(4));
                } else if (parsedList.size() == 4) {
                    params = insertEvent(parsedList.get(1), parsedList.get(2), parsedList.get(3));
                }
                url = UrlCreator.getInsertEventUrl(receiverCalenderId);
//                requestCreator.makeRequestForCalendar(url, params, RequestConstants.POST.getValue());
                break;
            case "update" :
                if (parsedList.size() == 5) {
                    params =  updateEvent(parsedList.get(2), parsedList.get(3), parsedList.get(4));
                } else if (parsedList.size() == 4) {
                    params = updateEvent(parsedList.get(2), parsedList.get(3));
                }
                url = UrlCreator.getUpdateEventUrl(receiverCalenderId, parsedList.get(1));
//                requestCreator.makePutRequest(url, params);
                break;
            case "delete" :
                url = UrlCreator.getDeleteEventUrl(receiverCalenderId, parsedList.get(1));
//                requestCreator.makeDeleteRequest(url, params);
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
