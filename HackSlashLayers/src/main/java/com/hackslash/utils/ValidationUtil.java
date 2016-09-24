package com.hackslash.utils;

/**
 * Created by apple on 24/09/16.
 */

import com.google.gson.JsonElement;
import com.hackslash.constants.SpecialChars;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationUtil {

    public static boolean isStringSet(String value) {
        return (value != null && !value.equals(SpecialChars.BLANK_STRING.getValue()));
    }

    public static boolean isListNotEmpty(List value) {
        return (value !=null && !value.isEmpty());
    }

    public static boolean isMapNotEmpty(Map value) {
        return (value !=null && !value.isEmpty());
    }

    public static String safeReturn(List<String> list, String key) {
        return list.contains(key) ? key : SpecialChars.BLANK_STRING.getValue();
    }

    public static String safeReturn(String[] list, int index) {
        return list.length >= index ? list[index] : SpecialChars.BLANK_STRING.getValue();
    }

    public static String safeReturnForJsonObj(JSONObject jsonObject, String key) {
        return jsonObject.containsKey(key) && jsonObject.get(key) != null ? jsonObject.get(key).toString() : SpecialChars.BLANK_STRING.getValue();
    }

    public static String safeReturn(Map<String, String> map, String key) {
        return map.containsKey(key) && map.get(key) != null ? map.get(key) : SpecialChars.BLANK_STRING.getValue();
    }

    public static Map<String, String> safeReturnMap(Map<String, Map<String, String>> map, String key) {
        return (Map<String, String>) (map.containsKey(key) && map.get(key) != null ? map.get(key) : new HashMap<>());
    }

    public static String safeReturn(Map<String, String> map, String key, String defaultValue) {
        return map.containsKey(key) && map.get(key) != null ? map.get(key) : defaultValue;
    }

    public static String safeReturn(String key) {
        return key != null ? key : SpecialChars.BLANK_STRING.getValue();
    }

    public static Long safeReturn(Long key) {
        return key != null ? key : (long)0;
    }

    public static boolean isNotNullOrEmpty(byte[] array) {
        return (array != null && array.length > 0);
    }

    public static boolean isNotNull(byte[] array) {
        return array != null;
    }

    public static String safeReturn(JsonElement jsonElement) {
        return jsonElement != null ? jsonElement.toString() : SpecialChars.BLANK_STRING.getValue();
    }
}

