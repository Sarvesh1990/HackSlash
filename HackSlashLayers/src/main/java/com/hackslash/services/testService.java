package com.hackslash.services;

import com.hackslash.utils.JsonUtil;
import com.hackslash.utils.RequestParse;
import com.sun.org.apache.regexp.internal.RE;

import javax.jws.WebService;
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

@WebServlet(name = "testService", urlPatterns = "/testService", loadOnStartup = 1)
public class testService extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Request came");
        System.out.println("Request parameters map : " + JsonUtil.jsonEncode(request.getParameterMap()));
    }

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
                     SendMessage.sendMessage(requestMap.get("chat"), requestMap.get("text"), "612c554a-2b27-49f1-bb2f-8a2b8d2119dc");
                 } catch (Exception e) {
                     System.out.println("error");
                     e.printStackTrace();
                 }
             }
        }
    }
}

curl 'https://content.googleapis.com/calendar/v3/users/me/calendarList?maxResults=3&key=AIzaSyD-a9IF8KKYgoC3cpgS-Al7hLQDbugrDcw' -H 'x-goog-encode-response-if-executable: base64' -H 'accept-encoding: gzip, deflate, sdch, br' -H 'x-origin: https://developers.google.com' -H 'accept-language: en-US,en;q=0.8' -H 'authorization: Bearer ya29.Ci9oAzGFA5TI35338MPn_mCua6Z_JQK3jRx_2tui2axxf8AmMWlQ0LpxmSn9KUFj7w' -H 'x-clientdetails: appVersion=5.0%20(Macintosh%3B%20Intel%20Mac%20OS%20X%2010_11_5)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F53.0.2785.116%20Safari%2F537.36&platform=MacIntel&userAgent=Mozilla%2F5.0%20(Macintosh%3B%20Intel%20Mac%20OS%20X%2010_11_5)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F53.0.2785.116%20Safari%2F537.36' -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36' -H 'accept: */*' -H 'referer: https://content.googleapis.com/static/proxy.html?jsh=m%3B%2F_%2Fscs%2Fabc-static%2F_%2Fjs%2Fk%3Dgapi.gapi.en.I_ZVJb_pzks.O%2Fm%3D__features__%2Frt%3Dj%2Fd%3D1%2Frs%3DAHpOoo8NNXa6ND5OamMO-moF9Aq-DewphA' -H 'authority: content.googleapis.com' -H 'x-javascript-user-agent: google-api-javascript-client/1.1.0-beta' -H 'x-referer: https://developers.google.com' --compressed

//
//https://accounts.google.com/o/oauth2/auth?access_type=offline&approval_prompt=force&scope=https%253A%252F%252Fwww.googleapis.com%252Fauth%252Fcalendar%2Bhttps%253A%252F%252Fwww.googleapis.com%252Fauth%252Fuserinfo.profile&response_type=code&redirect_uri=https%253A%252F%252Fapps.flock.co%252Fmanage%252Foauth%252Fcallback%252Fgoogle-drive&state=%257B%2522flockValidationToken%2522%253A%2522eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBJZCI6Imdvb2dsZS1kcml2ZSIsInVzZXJJZCI6InU6OTc5NWc0dzloNWdqcWtoaCIsImV4cCI6MTQ3NTMyMzg4MSwiaWF0IjoxNDc0NzE5MDgxLCJqdGkiOiJiMzllYjJjNi1hZTVhLTQ1NTMtYTk0OS1lNDEwMWMxNDA2NzEifQ.ezcumdFece-c0hbqHohllCq66ym6VZsl9V5ICrF-bKU%2522%252C%2522csrf_token%2522%253A%2522uibsv4unasukf75qk2s1jv7v0i%2522%252C%2522user_token%2522%253A%2522ab202379-e0fb-4233-8eaf-4ef789055afe%2522%257D&client_id=352466414191-vrck3ilg3h8ini0co3tc70utr6j67mr3.apps.googleusercontent.com&from_login=1&as=-6913538795344b20&authuser=0&flockValidationToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBJZCI6ImRjZjhhMDk2LWU4M2UtNDZkNS04Nzc4LTQ0N2JjMTUxNzgyNiIsInVzZXJJZCI6InU6OTc5NWc0dzloNWdqcWtoaCIsImV4cCI6MTQ3NTMyNDM3MCwiaWF0IjoxNDc0NzE5NTcwLCJqdGkiOiI1OGJlZDZhOC0xMTA0LTRhZDItYWEyNi1mYWFjZmQ2Mjc4OTMifQ.mKk7jWEx6F9y0xhUXS1QOr3Wyktal-1FUcJaeCUKFkw
//
//https://accounts.google.com/o/oauth2/auth?access_type=offline&approval_prompt=force&scope=https://www.googleapis.com/auth/calendar&response_type=code&redirect_uri=http://47e2c11b.ngrok.io/HackSlashService/testService&client_id=932312280716-1hhafl2r0vac4vs82f04vg17cee6rdhp.apps.googleusercontent.com&from_login=1&as=-6913538795344b20&authuser=0
//
//
//        https://apps.flock.co/manage/google-drive/new?flockValidationToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBJZCI6Imdvb2dsZS1kcml2ZSIsInVzZXJJZCI6InU6OTc5NWc0dzloNWdqcWtoaCIsImV4cCI6MTQ3NTMyNTA3OSwiaWF0IjoxNDc0NzIwMjc5LCJqdGkiOiI0OWI1M2QyZS0wYWUwLTQwOTgtYTzzAxNS1iODk3Y2M0NjhmYjgifQ.C2SUL1hpZ3UsMxwOo72bLswvI5qwdqHLPr07Hu4fU58




        https://accounts.google.com/o/oauth2/token?code=4/NdW86OHcRoHI7eRKdYILyKIiEFgtPdwatoMZahSbr0g&client_id=932312280716-1hhafl2r0vac4vs82f04vg17cee6rdhp.apps.googleusercontent.com&client_secret=gLvLwCZRZLlB-9_l69gyvFzL&redirect_uri=urn:ietf:wg:oauth:2.0:oob&grant_type=authorization_code