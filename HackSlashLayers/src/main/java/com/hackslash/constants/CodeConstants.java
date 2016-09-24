package com.hackslash.constants;

/**
 * Created by ankit.go on 25-09-2016.
 */
public enum CodeConstants {

    GMT_TIME("+05:30");

    private String value;
    private byte[] bytes;

    public String getValue() {
        return value;
    }

    public byte[] getBytes() {
        return bytes;
    }

    CodeConstants(String value) {
        this.value = value;
        this.bytes = value.getBytes();
    }


}
