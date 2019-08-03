package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/messages")
    @SendTo("/topic/conversation")
    public Content greeting(Message message) throws Exception {
        return new Content(HtmlUtils.htmlEscape(message.getName() + ": " + message.getValue()));
    }

}
