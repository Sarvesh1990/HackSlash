package com.hackslash.constants;

import com.hackslash.pojos.UserTokenDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 25/09/16.
 */
public class Constants {
    public static final Map<String, UserTokenDetails> USER_TOKEN_MAP = new HashMap<>();
    public static final String APP_SECRET =  "6a0866c2-2d43-4954-b203-3a2ca3cad51b";
    public static final String AUTH_DOMAIN = "http://9e4e1f73.ngrok.io";
    public static final String OAUTH_DOMAIN = "https://accounts.google.com/o/oauth2/token";
    public static final String REDIRECT_URL = "http://47e2c11b.ngrok.io/HackSlashService/googleApis";
    public static final String CLIENT_ID = "297408651022-5hogd5neoi4om4iqajsc7fqih1enajbo.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "NaaYhfOrSliVs0URPlxvNjFS";
    public static final String API_KEY = "AIzaSyBsAR7EZeJnmlOU0DwZfT1jeHBVwob7C_o";
    public static final String GROUP_MEMBERS = "https://api.flock.co/v1/groups.getMembers";
    public static final String SEND_MESSAGE = "https://api.flock.co/v1/chat.sendMessage";
    public static final Map<String, Map<Boolean, String>> MESSAGE = new HashMap<String, Map<Boolean, String>>() {
        {
            put(MessageTypes.MESSAGE_ADD_MEETING.getValue(), new HashMap<Boolean, String>(){
                {
                    put(true, "Let's have a meeting");
                    put(false, "Please download app");
                }
            });
        }
    };
}
