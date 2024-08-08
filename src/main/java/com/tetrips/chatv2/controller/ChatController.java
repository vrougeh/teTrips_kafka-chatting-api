package com.tetrips.chatv2.controller;

import com.tetrips.chatv2.Service.ChatService;
import com.tetrips.chatv2.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ChatController {
  private final ChatService chatService;

  @MessageMapping("/send")
  public void sendMessage(ChatMessage message) {
    chatService.saveMessage(message).subscribe();
  }

  @RestController
  @RequestMapping("/api/messages")
  @RequiredArgsConstructor
  public static class ChatRestController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody ChatMessage message) {
      ChatMessage savedMessage = chatService.saveMessage(message).block();
      return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/{prId}")
    public ResponseEntity<List<ChatMessage>> getAllMessagesByPrId(@PathVariable String prId) {
      List<ChatMessage> messages = chatService.getAllMessagesByPrId(prId).collectList().block();
      return ResponseEntity.ok(messages);
    }
  }
}