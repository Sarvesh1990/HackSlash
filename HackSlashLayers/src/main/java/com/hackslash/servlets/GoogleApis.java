package com.hackslash.servlets;

import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.hackslash.constants.Constants;
import com.hackslash.constants.RequestConstants;
import com.hackslash.pojos.UserTokenDetails;
import com.hackslash.utils.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.hackslash.utils.ValidationUtil.safeReturn;
import static com.hackslash.utils.ValidationUtil.isStringSet;

/**
 * Created by apple on 24/09/16.
 */

@WebServlet(name = "googleApis", urlPatterns = "/googleApis", loadOnStartup = 1)
public class GoogleApis extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
            if(!isStringSet(request.getParameter(RequestConstants.CODE.getValue())) || !isStringSet(request.getParameter(RequestConstants.STATE.getValue()))) {
                System.out.println("Error in google Api");
                return;
            }

            Map<String, String> stateMap = JsonUtil.jsonDecodeMap(request.getParameter(RequestConstants.STATE.getValue()));

            if(Constants.USER_TOKEN_MAP.get(stateMap.get(RequestConstants.USER_ID.getValue())) != null && Constants.USER_TOKEN_MAP.get(stateMap.get(RequestConstants.USER_ID.getValue())).getFlockToken()
                    .equals(stateMap.get(RequestConstants.USER_TOKEN.getValue()))) {
                try {
                    TokenResponse tokenResponse =
                        new AuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(),
                                new GenericUrl(Constants.OAUTH_DOMAIN), request.getParameter(RequestConstants.CODE.getValue()))
                                .setRedirectUri(Constants.REDIRECT_URL)
                                .setClientAuthentication(
                                        new ClientParametersAuthentication(Constants.CLIENT_ID,
                                                Constants.CLIENT_SECRET)).execute();
                    UserTokenDetails tokenDetails = Constants.USER_TOKEN_MAP.get(stateMap.get(RequestConstants.USER_ID.getValue()));
                    tokenDetails.setCalendarApiAuthToken(tokenResponse.getAccessToken());
                    tokenDetails.setCalendarApiRefreshToken(tokenResponse.getRefreshToken());
                    tokenDetails.setCalendarTokenExpiry(System.currentTimeMillis() + tokenResponse.getExpiresInSeconds()*1000);

                    System.out.println("User details : "  + JsonUtil.jsonEncode(Constants.USER_TOKEN_MAP.get(stateMap.get(RequestConstants.USER_ID.getValue()))));
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
}

