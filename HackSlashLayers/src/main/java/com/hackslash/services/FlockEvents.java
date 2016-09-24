package com.hackslash.services;

import com.hackslash.utils.JsonUtil;
import com.hackslash.utils.RequestParse;
import com.sun.org.apache.regexp.internal.RE;

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
        System.out.println("Request came");
        System.out.println("Request parameters map : " + JsonUtil.jsonEncode(request.getParameter("userId")));
        System.out.println("Headers : " + JsonUtil.jsonEncode(request.getHeader("x-flock-validation-token")));
        String body = RequestParse.getBody(request);
        System.out.println("Body : " + body);
        Map<String, String> requestMap = JsonUtil.jsonDecodeMap(body);
        if (isStringSet(safeReturn(requestMap, "name"))){
             if(requestMap.get("name").equals("client.slashCommand")) {
                 try {
//                     SendMessage.sendMessage(requestMap.get("chat"), requestMap.get("text"), "612c554a-2b27-49f1-bb2f-8a2b8d2119dc");


                 } catch (Exception e) {
                     System.out.println("error");
                     e.printStackTrace();
                 }
             }
        }
    }
}