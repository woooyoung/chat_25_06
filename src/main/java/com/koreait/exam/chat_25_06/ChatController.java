package com.koreait.exam.chat_25_06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public record writeChatMessageResponse(long id) {

    }


    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeChatMessageResponse> writeMessage() {
        ChatMessage message = new ChatMessage("홍길동", "안녕");

        chatMessages.add(message);

        return new RsData<>(
                "S-1",
                "메세지가 작성됨",
                new writeChatMessageResponse(message.getId())
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
