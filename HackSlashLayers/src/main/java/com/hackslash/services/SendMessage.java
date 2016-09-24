package com.hackslash.services;

import co.flock.www.FlockApiClient;
import co.flock.www.model.messages.FlockMessage;
import co.flock.www.model.messages.Message;

/**
 * Created by apple on 24/09/16.
 */
public class SendMessage {
    public static void sendMessage(String userId, String sendMessage, String token) throws Exception {
        FlockApiClient flockApiClient = new FlockApiClient(token);
        Message message = new Message(userId, sendMessage);
        FlockMessage flockMessage = new FlockMessage(message);
        String messageId = flockApiClient.chatSendMessage(flockMessage);
    }
}


