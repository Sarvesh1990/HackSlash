package com.hackslash.listeners;

import com.hackslash.constants.Constants;
import com.hackslash.pojos.UserTokenDetails;
import com.hackslash.utils.JsonUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by apple on 25/09/16.
 */
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UserTokenDetails tokenDetails = new UserTokenDetails("954dea38-b467-4088-b7dc-131c6f4cf315", "Bearer ya29.CjBpAz5oungfXAWrVHOQ-lm2mLCmfBCsSwCMnV7StWXon-DvcWZnq0nlhR5xXqEUuqY",
                "1/Duenvt0dbir31_wAVcKeTWSxrQVUEylibZTCO8UVSsc", 1474761688716L, "sarveshiitb@gmail.com", true);
        Constants.USER_TOKEN_MAP.put("u:9795g4w9h5gjqkhh", tokenDetails);
        System.out.println("Map : " + JsonUtil.jsonEncode(Constants.USER_TOKEN_MAP));

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Testing");

    }
}
