package com.hackslash.utils;

import com.hackslash.constants.Constants;
import com.hackslash.constants.RequestConstants;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ankit.go on 25-09-2016.
 */
public class RequestCreator {
    private String userToken;
    public RequestCreator(String userToken) {
        this.userToken = userToken;
    }

    public String makeRequestForCalendar(String url, String params, String requestType, String userId) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add variable reuqest header
        if(Constants.USER_TOKEN_MAP.get(userId).getCalendarTokenExpiry() <= System.currentTimeMillis() - 100) {
            RefreshToken.refreshToken(Constants.USER_TOKEN_MAP.get(userId));
        }

        con.setRequestProperty("Authorization", userToken);
        if(requestType.equals(RequestConstants.GET.getValue())) {
            return doGet(con);
        } else {
            return doPost(con, params, requestType);
        }
    }

    public String makeRequestForFlock(String url, String params, String requestType) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add variable reuqest header
        con.setRequestProperty("X-Flock-User-Token", userToken);

        if(requestType.equals(RequestConstants.GET.getValue())) {
            return doGet(con);
        } else {
            return doPost(con, params, requestType);
        }
    }

    public String doPost(HttpsURLConnection con, String params, String requestType) throws IOException {
        String output;
        con.setRequestMethod(requestType);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        System.out.println("Params map : " + params + ", request type : " + requestType);

        con.setRequestProperty("Content-Type", "application/json");
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        output = response.toString();
        //print result
        System.out.println(output);
        return output;
    }

    private String doGet(HttpsURLConnection con) throws Exception {
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String output = response.toString();
        System.out.println(output);
        return output;

    }
}
