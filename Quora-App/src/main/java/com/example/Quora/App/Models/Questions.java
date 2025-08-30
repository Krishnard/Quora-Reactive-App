package com.example.Quora.App.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questions")
public class Questions {
    
    @Id
    private String Id;
    
    @NotBlank(message = "Title question cannot be blank")
    @Size(min = 10, max = 200, message = "Title question must be between 10 and 200 characters")
    private String title;
    
    @NotBlank(message = "Content cannot be blank")
    @Size(min = 20, max = 1000, message = "Content must be at least 20 characters long")
    private String content;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
}
