package com.hackslash.utils;

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
    private String flockApiUserToken;
    public RequestCreator(String flockApiUserToken) {
        this.flockApiUserToken = flockApiUserToken;
    }

    public void makeRequestForCalendar(String url, String params, String authorizationToken, String requestType) throws IOException {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add variable reuqest header
        con.setRequestProperty("Authorization", authorizationToken);
        doRequest(con, params, requestType);
    }

    public void makeRequestForFlock(String url, String params, String requestType) throws IOException {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add variable reuqest header
        con.setRequestProperty("X-Flock-User-Token", flockApiUserToken);
        doRequest(con, params, requestType);
    }

    public void doRequest(HttpsURLConnection con, String params, String requestType) throws IOException {
        con.setRequestMethod(requestType);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


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

        //print result
        System.out.println(response.toString());

    }
}
