package com.hackslash.constants;

/**
 * Created by apple on 24/09/16.
 */
public enum RequestConstants {

    FLOCK_REQUEST_NAME("name")
    , APP_INSTALL("app.install")
    , SLASH_COMMANDS("client.slashCommand")
    , APP_UNINSTALL("app.uninstall");

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