package com.hackslash.servlets;

import com.hackslash.constants.RequestConstants;
import com.hackslash.utils.JsonUtil;
import com.hackslash.utils.ParseUtil;

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

@WebServlet(name = "flockEvents", urlPatterns = "/flockEvents", loadOnStartup = 1)
public class FlockEvents extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> requestMap = JsonUtil.jsonDecodeMap(ParseUtil.getPostRequestBody(request));
        System.out.println("Request Map is : " + JsonUtil.jsonEncode(requestMap));

        if (requestMap.get(RequestConstants.FLOCK_REQUEST_NAME.getValue()) != null){
            if(requestMap.get(RequestConstants.FLOCK_REQUEST_NAME.getValue()).equals(RequestConstants.APP_INSTALL.getValue())) {

            } else if (requestMap.get(RequestConstants.FLOCK_REQUEST_NAME.getValue()).equals(RequestConstants.SLASH_COMMANDS.getValue())) {

            } else if (requestMap.get(RequestConstants.FLOCK_REQUEST_NAME.getValue()).equals(RequestConstants.APP_UNINSTALL.getValue())) {

            }
        }
    }
}