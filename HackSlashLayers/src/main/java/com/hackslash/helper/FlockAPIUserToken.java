package com.hackslash.helper;

import com.hackslash.constants.Constants;
import com.hackslash.pojos.UserTokenDetails;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class FlockAPIUserToken {

    public static String  getAuthToken(String userId) {
        UserTokenDetails userTokenDetails =  Constants.USER_TOKEN_MAP.get(userId);
        return userTokenDetails.getFlockToken();
    }
}
