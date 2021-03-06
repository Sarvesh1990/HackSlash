package com.hackslash.servlets;

import co.flock.www.JWTToken;
import co.flock.www.model.JWT.JWTPayload;
import com.hackslash.constants.Constants;
import com.hackslash.constants.RequestConstants;
import com.hackslash.pojos.UserCalenderIPMap;
import com.hackslash.pojos.UserTokenDetails;
import com.hackslash.services.CalenderListService;
import com.hackslash.services.SlashService;
import com.hackslash.utils.JsonUtil;
import com.hackslash.utils.ParseUtil;
import org.json.simple.parser.ParseException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.hackslash.utils.ValidationUtil.isStringSet;

/**
 * Created by apple on 24/09/16.
 */

@WebServlet(name = "flockEvents", urlPatterns = "/flockEvents", loadOnStartup = 1)
public class FlockEvents extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JWTPayload jwtPayload = null;
        try {
            jwtPayload = JWTToken.GetJWTPayload(request.getParameter(RequestConstants.FLOCK_VALIDATION_TOKEN.getValue()), Constants.APP_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Constants.USER_TOKEN_MAP.get(jwtPayload.getUserId()) != null && isStringSet(Constants.USER_TOKEN_MAP.get(jwtPayload.getUserId()).getFlockToken())) {
            response.sendRedirect(Constants.AUTH_DOMAIN + "/authenticate?userId=" + jwtPayload.getUserId() + "&userToken=" + Constants.USER_TOKEN_MAP.get(jwtPayload.getUserId()).getFlockToken());
        } else {
            response.sendRedirect(Constants.AUTH_DOMAIN + "/error");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> requestMap = JsonUtil.jsonDecodeMap(ParseUtil.getPostRequestBody(request));
        System.out.println("Request Map is : " + JsonUtil.jsonEncode(requestMap));

        if (requestMap.get(RequestConstants.FLOCK_REQUEST_NAME.getValue()) != null){
            if(requestMap.get(RequestConstants.FLOCK_REQUEST_NAME.getValue()).equals(RequestConstants.APP_INSTALL.getValue())) {
                String userToken = requestMap.get(RequestConstants.USER_TOKEN.getValue());
                Constants.USER_TOKEN_MAP.put(requestMap.get(RequestConstants.USER_ID.getValue()), new UserTokenDetails(userToken, null, null, null, null, true));
                System.out.println("User details first : "  + JsonUtil.jsonEncode(Constants.USER_TOKEN_MAP.get(requestMap.get(RequestConstants.USER_ID.getValue()))));
            } else if (requestMap.get(RequestConstants.FLOCK_REQUEST_NAME.getValue()).equals(RequestConstants.SLASH_COMMANDS.getValue())) {
                System.out.println("Slash : " + JsonUtil.jsonEncode(requestMap));
                try {
                    new SlashService(requestMap, response).processRequest();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestMap.get(RequestConstants.FLOCK_REQUEST_NAME.getValue()).equals(RequestConstants.APP_UNINSTALL.getValue())) {
                Constants.USER_TOKEN_MAP.get(requestMap.get(RequestConstants.USER_ID.getValue())).setIsActive(false);
            }
        }
    }
}


