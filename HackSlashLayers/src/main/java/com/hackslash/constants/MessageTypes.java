package com.hackslash.constants;

/**
 * Created by ankit.go on 25-09-2016.
 */
public enum MessageTypes {

    MESSAGE_ADD_MEETING("ADD_MEETING");

    private String value;
    private byte[] bytes;

    public String getValue() {
        return value;
    }

    public byte[] getBytes() {
        return bytes;
    }

    MessageTypes(String value) {
        this.value = value;
        this.bytes = value.getBytes();
    }


}
