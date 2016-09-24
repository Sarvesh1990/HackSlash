package com.hackslash.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.hackslash.constants.SpecialChars;
import com.hackslash.utils.ValidationUtil;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created with IntelliJ IDEA.
 * User: ankit.sh
 * Date: 3/4/2016
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtil {
    private static final Logger logger = Logger.getLogger(JsonUtil.class);
    private static Gson gson;
    private static Gson gsonSerializeNulls;
	private static Gson prettyGson;

	public static Gson getPrettyGson() {
		if(prettyGson == null) {
			prettyGson = new GsonBuilder().setPrettyPrinting().create();
		}
		return prettyGson;
	}

    public static Gson getGson() {
        if(gson == null) {
            gson = new GsonBuilder().disableHtmlEscaping().create();
        }
        return gson;
    }

	public static String getPrettyJson(Object object) {
		return getPrettyGson().toJson(object);
	}
    
    public static Gson getGsonSerializeNulls() {
    	if(gsonSerializeNulls == null) {
    		synchronized (JsonUtil.class) {
				if(gsonSerializeNulls == null) {
					gsonSerializeNulls = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
				}
			}
    	}
    	return gsonSerializeNulls;
    }
    
    public static String getEmptyJsonString() {
		return SpecialChars.EMPTY_JSON_STRING.getValue();
	}

    public static String jsonEncode(Object object){
        String jsonString = getGson().toJson(object);
        return jsonString != null ? jsonString : SpecialChars.BLANK_STRING.getValue();
    }
    
    public static String jsonEncodeSerializeNulls(Object object){
        String jsonString = getGsonSerializeNulls().toJson(object);
        return jsonString != null ? jsonString : SpecialChars.BLANK_STRING.getValue();
    }

    public static String jsonEncodeMap(Map<String, Map<String, String>> map) {
        String jsonString =  getGson().toJson(map, new TypeToken<Map<String, Map<String, String>>>(){}.getType());
        return jsonString != null ? jsonString : SpecialChars.BLANK_STRING.getValue();
    }

    public static String jsonDecodeString(String jsonString) {
        String jsonObject = getGson().fromJson(jsonString, String.class);
        return jsonObject != null ? jsonObject : SpecialChars.BLANK_STRING.getValue();
    }
    
    public static ArrayList jsonDecodeArray(String jsonString) {
        ArrayList jsonObject = getGson().fromJson(jsonString, new TypeToken<ArrayList<String>>() {
		}.getType());
        return jsonObject != null ? jsonObject : new ArrayList();
    }

    public static Map<String, String> jsonDecodeMap(String jsonString) {
        Map<String, String> jsonObject = getGson().fromJson(jsonString, new TypeToken<Map<String, String>>() {
		}.getType());
        return (Map<String, String>) (jsonObject != null ? jsonObject : new HashMap<>());
    }

    public static Map<String, Map<String, String>> jsonDecodeMapOfMap(String jsonString) {
        Map<String, Map<String, String>> jsonObject = getGson().fromJson(jsonString, new TypeToken<Map<String, Map<String, String>>>() {
            }.getType());
        return (Map<String, Map<String, String>>) (jsonObject != null ? jsonObject : new HashMap<>());
    }
    
    public static List<Map<String, String>> jsonDecodeListOfMap(String jsonString) {
        List<Map<String, String>> jsonObject = getGson().fromJson(jsonString, new TypeToken<List<Map<String, String>>>() {
            }.getType());
        return (List<Map<String, String>>) (jsonObject != null ? jsonObject : new ArrayList<>());
    }

    
    public static List<Map<String, String>> jsonDecodeListOfMap(JsonArray jsonArray) {
        List<Map<String, String>> jsonObject = getGson().fromJson(jsonArray, new TypeToken<List<Map<String, String>>>() {
            }.getType());
        return (List<Map<String, String>>) (jsonObject != null ? jsonObject : new ArrayList<>());
    }
    
    public static List<Map<String, Object>> jsonDecodeListOfObject(JsonArray jsonArray) {
        List<Map<String, Object>> jsonObject = getGson().fromJson(jsonArray, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
        return (List<Map<String, Object>>) (jsonObject != null ? jsonObject : new ArrayList<>());
    }
    
	public static Object jsonDecodeObject(String jsonString) {
		return getGson().fromJson(jsonString, Object.class);
	}
	
	 public static Map<String, Object> jsonDecodeMapOfObjects(String jsonString) {
		 Map<String, Object> jsonObject = getGson().fromJson(jsonString, new TypeToken<Map<String, Object>>() {
			}.getType());
	        return (Map<String, Object>) (jsonObject != null ? jsonObject : new HashMap<>());
	 }

    public static Map<String, Integer> getJsonToStringIntMap(String jsonString) {
        Map<String, Integer> jsonDecodedMap = getGson().<Map<String, Integer>>fromJson(jsonString, new TypeToken<Map<String, Integer>>() {}.getType());
        return (Map<String, Integer>) (jsonDecodedMap != null ? jsonDecodedMap : new HashMap<>());
    }

    public static boolean isValidJsonMap(String jsonString) {
    	try {
    		return jsonString != null && jsonDecodeMap(jsonString) != null && !jsonDecodeMap(jsonString).isEmpty();
    	}catch (JsonSyntaxException e) {
    		logger.info("[isValidJsonMap] | the below is not valid json Map " + jsonString);
    	}
    	return false;
    }
   
    public static boolean isValidJsonMapOfObjects(String jsonString) {
    	try {
    		if(jsonString != null) {
    			Map<String, Object> result = jsonDecodeMapOfObjects(jsonString);
    			return result != null && !result.isEmpty();
    		}
    	}catch (JsonSyntaxException e) {
    		logger.info("[isValidJsonMap] | the below is not valid json Map " + jsonString);
    	}
    	return false;
    }
    
    public static boolean isValidJsonArray(String jsonString) {
    	try {
    		return jsonString != null && jsonDecodeArray(jsonString) != null && !jsonDecodeArray(jsonString).isEmpty();
    	}catch (JsonSyntaxException e) {
    		logger.info("[isValidJsonArray] | the below is not valid json array " + jsonString);
    	}
    	return false;
    }
    
    public static boolean isValidJson(String jsonString) {
    	try {
    		return jsonString != null && getGson().fromJson(jsonString, Object.class) != null;
    	}catch (JsonSyntaxException e) {
    		logger.info("[isValidJson] | the below is not valid json " + jsonString);
    	}
    	return false;
    }

    public static boolean isValidJsonString(String jsonInString) {
    	try {
    		return jsonInString != null && ValidationUtil.isStringSet(jsonDecodeString(jsonInString));
    	}catch (JsonSyntaxException e) {
    		logger.info("[isValidJsonString] | the below is not valid json string " + jsonInString);
    	}
    	return false;
    }

    public static final JsonObject getJsonObject(String jsonString) throws Exception {
        JsonParser jsonParser = new JsonParser();
        if(!ValidationUtil.isStringSet(jsonString)) {
            return new JsonObject();
        }
        try {
            return (JsonObject) jsonParser.parse(jsonString);
        } catch (Exception e) {
            logger.error("[getJsonObject(String jsonString)] | Error converting jsonString to proper json object. JsonString : " + jsonString);
            throw e;
        }
    }

	public static final JsonArray getJsonArray(String jsonString) throws Exception {
		JsonParser jsonParser = new JsonParser();
		if(!ValidationUtil.isStringSet(jsonString)) {
			return new JsonArray();
		}
		try {
			return (JsonArray) jsonParser.parse(jsonString);
		} catch (Exception e) {
			logger.error("[getJsonArray(String jsonString)] | Error converting jsonString to proper json array. JsonString : " + jsonString);
			throw e;
		}
	}
	
	public static void main(String[] a) {
		System.out.println(jsonEncode("{\"content\":\"\n" + 
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\"}"));
	}
    
   /* public static final String getJsonPairUrlEncode(String key, String value) {
    	StringBuilder json = new StringBuilder();
    	json.append(key);
		json.append(SpecialChars.SPACE.getValue());
		json.append(SpecialChars.COLON.getValue());
		json.append(SpecialChars.SPACE.getValue());
		json.append(SpecialChars.DOUBLE_QUOTE.getValue());
		if (ValidationUtil.isStringSet(value)) {
			try {
				json.append(StaticUtil.urlEncodeUTF8(value));
			} catch (UnsupportedEncodingException e) {
	            logger.error("[getJsonPairUrlEncode(String , String)] | Error urlencoding : " + value);
			}
		}
		json.append(SpecialChars.DOUBLE_QUOTE.getValue());
		return json.toString();
    }
    */
    public static final String getJson(Map<String, String> params) {
    	StringBuilder json = new StringBuilder();
    	if(params != null && !params.isEmpty()) {
    		json.append(SpecialChars.OPENING_CURLY_BRACES.getValue());
    		json.append(getJsonPairs(params));
    		json.append(SpecialChars.CLOSING_CURLY_BRACES.getValue());
    		return json.toString();
    	}
		return SpecialChars.EMPTY_JSON_STRING.getValue();
    }
    
    public static final String getJsonPairs(Map<String, String> params) {
    	int count = 0;
    	StringBuilder json = new StringBuilder();
		for(Entry<String, String> entry : params.entrySet()) {
			json.append(getJsonPair(entry.getKey(), entry.getValue()));
			if(count < params.size()-1) {
				json.append(SpecialChars.COMMA.getValue());
				json.append(SpecialChars.SPACE.getValue());
			}
			count++;
		}
		return json.toString();
    }
    
    public static final String getJsonPair(String key, String value) {
    	StringBuilder json = new StringBuilder();
    	json.append(key);
		json.append(SpecialChars.SPACE.getValue());
		json.append(SpecialChars.COLON.getValue());
		json.append(SpecialChars.SPACE.getValue());
		json.append(SpecialChars.DOUBLE_QUOTE.getValue());
		json.append(value);
		json.append(SpecialChars.DOUBLE_QUOTE.getValue());
		return json.toString();
    }
    
    public static final String getJsonPairsWithoutQuotes(Map<String, String> params) {
    	int count = 1;
    	StringBuilder json = new StringBuilder();
		for(Entry<String, String> entry : params.entrySet()) {
			json.append(getJsonPairWithoutQuotes(entry.getKey(), entry.getValue()));
			if(count < params.size()) {
				json.append(SpecialChars.COMMA.getValue());
				json.append(SpecialChars.SPACE.getValue());
			}
			count++;
		}
		return json.toString();
    }
    
    public static final String getJsonPairWithoutQuotes(String key, String value) {
    	StringBuilder json = new StringBuilder();
    	json.append(key);
		json.append(SpecialChars.SPACE.getValue());
		json.append(SpecialChars.COLON.getValue());
		json.append(SpecialChars.SPACE.getValue());
		json.append(value);
		return json.toString();
    }
}
