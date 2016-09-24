package com.hackslash.constants;

/**
 * Created by apple on 24/09/16.
 */
public enum SpecialChars {

    PIPE("|")
    , BLANK_STRING("")
    , BLANK_ARRAY("[]")
    , BLANK_MAP("{}")
    , COMMA(",")
    , SEMI_COLON(";")
    , COLON(":")
    , SLASH("/")
    , SINGLE_QUOTE("'")
    , BACK_SLASH("\\")
    , HOST_NOT_FOUND("--")
    , SPACE(" ")
    , UNDERSCORE("_")
    , QUESTION_MARK("?")
    , NO_VALUE_REPLACE_STRING("??")
    , ENCRYPTED_PARAM_SEPARATOR("@#@")
    , IMAGE_SIZE_SEPARATOR("x")
    , HASH("#")
    , AMPERSAND("&")
    , EQUALS("=")
    , HYPHEN("-")
    , ATMARK("@")
    , PERCENT_FORTY("%40")
    , SINGLE_QOUTE("'")
    , NULL("NULL")
    , PLUS("+")
    , AD_ENCODED_STRING_SEP("||")
    , AD_ATTRIBUTE_NAME_SEP("\\x1e")
    , AD_NAME_VALUE_SEP("\\x1d")
    , JS_AD_CLICK_DATA_DELIM("@@@")
    , PERCENTAGE("%")
    , LINE_BREAK("\n")
    , NEW_LINE("\r\n")
    , TAB("\t")
    , REDIS_SEPARATOR("`!`!`")
    , REGEX_END("/i")
    , UTF_ENCODING("UTF-8")
    , OPENING_CURLY_BRACES("{")
    , CLOSING_CURLY_BRACES("}")
    , DOUBLE_QUOTE("\"")
    , SERVER_HOSTS_SEPERATOR("@#@")
    , DEFAULT_IV("XXXXXXXX")
    , CIPHER_KEY("KLdfd34ASDFj$%@sdfs3fsdf2SFEWXS")
    , STAR("*")
    , EMPTY_JSON_STRING("{}")
    , OPENING_BRACKET("(")
    , CLOSING_BRACKET(")")
    , BACK_SLASH_SINGLE_QUOTE("\'")
    , OPENING_BRACKET_SINGLE_QUOTE("('")
    , SINGLE_QUOTE_CLOSING_BRACKET("')")
    , GREATER_THAN(">")
    , LESS_THAN("<")
    , DOT(".")
    , LOCATION_DETAILS_SEPARATOR("^~^")
    , NULL_OBJECT_VALUE("null")
    , CAESER_ENCRYPT_KEY("sdFlrDO6w94_aNInZitEo1LKByW5-vz8jhqSHcmCA=XkR0JVepPuU7b2xfg 3MT/GYQ");

    private String value;
    private byte[] bytes;

    public String getValue() {
        return value;
    }

    public byte[] getBytes() {
        return bytes;
    }

    SpecialChars(String value) {
        this.value = value;
        this.bytes = value.getBytes();
    }

}