package com.hackslash.utils;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.hackslash.constants.Constants;
import com.hackslash.pojos.UserTokenDetails;

import java.io.IOException;

/**
 * Created by apple on 24/09/16.
 */
public class RefreshToken {
    public static void refreshToken (UserTokenDetails userTokenDetails) {
        try {
            TokenResponse response =
                    new RefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(),
                            new GenericUrl(Constants.OAUTH_DOMAIN), userTokenDetails.getCalendarApiRefreshToken())
                            .setClientAuthentication(
                                    new ClientParametersAuthentication(Constants.CLIENT_ID,
                                            Constants.CLIENT_SECRET)).execute();
            System.out.println("Access token: " + response.getAccessToken());
            userTokenDetails.setCalendarApiAuthToken(response.getAccessToken());
        } catch (TokenResponseException e) {
            if (e.getDetails() != null) {
                System.err.println("Error: " + e.getDetails().getError());
                if (e.getDetails().getErrorDescription() != null) {
                    System.err.println(e.getDetails().getErrorDescription());
                }
                if (e.getDetails().getErrorUri() != null) {
                    System.err.println(e.getDetails().getErrorUri());
                }
            } else {
                System.err.println(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
