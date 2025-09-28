package com.example.Quora.App.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "likes")
public class Likes {

    @Id
    private String id;
    
    private String targetId; // ID of the question, answer, comment, post, or reel being liked
    
    // String can be replaced with Enum for better type safety
    private String targetType; // "question" or "answer" or "comment" or "post" or "reel"
    
    private Boolean isLike; // true for like, false for dislike
    
    @CreatedDate
    private LocalDateTime createdAt;
}
