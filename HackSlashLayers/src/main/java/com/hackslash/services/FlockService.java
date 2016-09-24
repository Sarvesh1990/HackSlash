package com.hackslash.services;

import com.hackslash.constants.Constants;
import com.hackslash.constants.RequestConstants;
import com.hackslash.utils.JsonUtil;
import com.hackslash.utils.RequestCreator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 25/09/16.
 */
public class FlockService {
    public void getUserListFromGroup (final String groupId, Map<String, String> requestMap) throws IOException {
        if(Constants.USER_TOKEN_MAP.get(requestMap.get(RequestConstants.USER_ID.getValue())) != null) {
            RequestCreator requestCreator = new RequestCreator(Constants.USER_TOKEN_MAP.get(requestMap.get(RequestConstants.USER_ID.getValue())).getFlockToken());
            Map<String, String> params = new HashMap<>();
            params.put(RequestConstants.GROUP_ID.getValue(), groupId);
            System.out.println("Params map : " + JsonUtil.jsonEncode(params) + ", GROUP Id : " + groupId);
            requestCreator.makeRequestForFlock(Constants.GROUP_MEMBERS, JsonUtil.jsonEncode(params), RequestConstants.POST.getValue());
        } else {
            System.out.println("User has not installed the app. How can he send message?");
        }

    }
}
