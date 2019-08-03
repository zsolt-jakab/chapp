package com.chat.app.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.chat.app.model.Content;
import com.chat.app.model.Message;

@Controller
public class ChatController {


    @MessageMapping("/messages")
    @SendTo("/topic/conversation")
    public Content greeting(Message message) throws Exception {
        return new Content(HtmlUtils.htmlEscape(message.getName() + ": " + message.getValue()));
    }

}
