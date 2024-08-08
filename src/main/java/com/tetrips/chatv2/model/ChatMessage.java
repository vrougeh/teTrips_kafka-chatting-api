package com.tetrips.chatv2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chatMessages")
public class ChatMessage {
  @Id
  private String id;
  private String nickname;
  private String message;
  private LocalDateTime chatTime;
  private String userId;
  private String prId;
}