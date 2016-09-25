package com.hackslash.services;

import com.hackslash.constants.Constants;
import com.hackslash.constants.MessageTypes;
import com.hackslash.constants.RequestConstants;
import com.hackslash.utils.JsonUtil;
import com.hackslash.utils.RequestCreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 25/09/16.
 */
public class FlockService {
    public ArrayList<Map<String, String>> getUserListFromGroup (String groupId, String flockToken) throws IOException {
        RequestCreator requestCreator = new RequestCreator(flockToken);
        Map<String, String> params = new HashMap<>();
        params.put(RequestConstants.GROUP_ID.getValue(), groupId);
        System.out.println("Params map : " + JsonUtil.jsonEncode(params) + ", GROUP Id : " + groupId);
        String groupMembers = requestCreator.makeRequestForFlock(Constants.GROUP_MEMBERS, JsonUtil.jsonEncode(params), RequestConstants.POST.getValue());
        ArrayList<Map<String, String>> groupMembersList = (ArrayList<Map<String, String>>) JsonUtil.jsonDecodeListOfMap(groupMembers);
        return groupMembersList;
    }

    public void sendMessageToUserList (String messageType, ArrayList<Map<String, String>> groupMembersList, String flockToken) throws IOException {
        for(Map<String, String> groupMemberDetails : groupMembersList) {
            if(Constants.USER_TOKEN_MAP.get(groupMemberDetails.get(RequestConstants.ID.getValue())) != null) {
                sendMessageToUser(messageType, groupMemberDetails.get(RequestConstants.ID.getValue()), flockToken, true);
            } else {
                sendMessageToUser(messageType, groupMemberDetails.get(RequestConstants.ID.getValue()), flockToken, false);
            }
        }
    }

    public void sendMessageToUser(String messageType, final String userId, String flockToken, final boolean appInstalled) throws IOException {
        RequestCreator requestCreator = new RequestCreator(flockToken);
        Map<String, Map<String, String>> params = new HashMap<>();
        Map<String, String> message = new HashMap<>();
        message.put(RequestConstants.TO.getValue(), userId);
        message.put(RequestConstants.TEXT.getValue(), Constants.MESSAGE.get(MessageTypes.MESSAGE_ADD_MEETING.getValue()).get(appInstalled));
        params.put(RequestConstants.MESSAGE.getValue(), message);xx`x
        System.out.println("Params map : " + JsonUtil.jsonEncode(params));
        requestCreator.makeRequestForFlock(Constants.SEND_MESSAGE, JsonUtil.jsonEncode(params), RequestConstants.POST.getValue());
    }
}
