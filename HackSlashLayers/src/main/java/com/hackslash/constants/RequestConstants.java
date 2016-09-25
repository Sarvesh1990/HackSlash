package com.hackslash.constants;

/**
 * Created by apple on 24/09/16.
 */
public enum RequestConstants {

    FLOCK_REQUEST_NAME("name")
    , APP_INSTALL("app.install")
    , SLASH_COMMANDS("client.slashCommand")
    , APP_UNINSTALL("app.uninstall")
    , USER_ID("userId")
    , USER_TOKEN("userToken")
    , CODE("code")
    , STATE("state")
    , FLOCK_VALIDATION_TOKEN("flockValidationToken")
    , CHAT("chat")
    , TEXT("text")
    , GROUP_ID("groupId")
    , POST("POST")
    , PUT("PUT")
    , DELETE("DELETE")
    , GET("GET")
    , ID("id")
    , MESSAGE("message")
    , TO("to");

    private String value;
    private byte[] bytes;

    public String getValue() {
        return value;
    }

    public byte[] getBytes() {
        return bytes;
    }

    RequestConstants(String value) {
        this.value = value;
        this.bytes = value.getBytes();
    }

}