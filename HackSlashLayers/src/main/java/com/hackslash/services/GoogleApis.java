package com.hackslash.services;

import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.hackslash.utils.JsonUtil;
import com.hackslash.utils.RefreshToken;
import com.hackslash.utils.RequestParse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.hackslash.utils.ValidationUtil.isStringSet;
import static com.hackslash.utils.ValidationUtil.safeReturn;

/**
 * Created by apple on 24/09/16.
 */

@WebServlet(name = "googleApis", urlPatterns = "/googleApis", loadOnStartup = 1)
public class GoogleApis extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Get Request came, Google Apis");
        System.out.println("Request parameters map : " + JsonUtil.jsonEncode(request.getParameterMap()));
        try {
            TokenResponse tokenResponse =
                    new AuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(),
                            new GenericUrl("https://accounts.google.com/o/oauth2/token"), request.getParameter("code"))
                            .setRedirectUri("http://47e2c11b.ngrok.io/HackSlashService/googleApis")
                            .setClientAuthentication(
                                    new ClientParametersAuthentication("297408651022-5hogd5neoi4om4iqajsc7fqih1enajbo.apps.googleusercontent.com",
                                            "NaaYhfOrSliVs0URPlxvNjFS")).execute();
            System.out.println("Access token: " + tokenResponse.getAccessToken() + ", expiry : " + tokenResponse.getExpiresInSeconds()
                + ", refresh token : " + tokenResponse.getRefreshToken());
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

