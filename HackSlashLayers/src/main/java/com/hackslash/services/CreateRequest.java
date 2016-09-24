package com.hackslash.services;

import org.apache.http.entity.StringEntity;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by apple on 24/09/16.
 */
public class CreateRequest {
    // HTTP POST request
    public static void sendPost() throws Exception {

        String url = " https://www.googleapis.com/calendar/v3/calendars/ankit.sh@media.net/events?key=AIzaSyAfYVVsnYFJ5qHWf1Ie1B_e0btgnsdn_yo";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String params = "{\"end\": {\"dateTime\": \"2016-09-28T11:00:00-05:00\"},\"start\": {\"dateTime\": \"2016-09-28T10:00:00-05:00\"},\"summary\": \"testasas\"}";
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer ya29.Ci9oAxtdEJ9bYDwJIN7NDu0kqKoRVmtIzMx802QQ2whrH9NwtXA3426VWyCBM-CuYA");
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

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

    public static void main(String[] args) throws Exception {
        sendPost();
    }

}
