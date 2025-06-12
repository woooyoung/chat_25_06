package com.koreait.exam.chat_25_06;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public record writeChatMessageResponse(long id, String authorName, String content) {

    }

//    @AllArgsConstructor
//    @Getter
//    public static class writeChatMessageRequest {
//        private final String authorName;
//        private final String content;
//    }

    public record writeChatMessageRequest(String authorName, String content) {
    }


    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeChatMessageResponse> writeMessage(@RequestBody writeChatMessageRequest req) {
        ChatMessage message = new ChatMessage(req.authorName, req.content);

        chatMessages.add(message);

        return new RsData<>(
                "S-1",
                "메세지가 작성됨",
                new writeChatMessageResponse(message.getId(), message.getAuthorName(), message.getContent())
        );
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<List<ChatMessage>> showMessages() {

        return new RsData<>(
                "S-1",
                "성공",
                chatMessages
        );
    }
}
