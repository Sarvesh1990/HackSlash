package com.hackslash.helper;

import com.hackslash.constants.Constants;
import com.hackslash.pojos.UserTokenDetails;
import com.hackslash.utils.JsonUtil;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class FlockAPIUserToken {

    public static String  getAuthToken(String userId) {
        System.out.println("User id : " + userId + " map : " + JsonUtil.jsonEncode(Constants.USER_TOKEN_MAP.get(userId)));
        UserTokenDetails userTokenDetails =  Constants.USER_TOKEN_MAP.get(userId);
        return userTokenDetails.getFlockToken();
    }
}
