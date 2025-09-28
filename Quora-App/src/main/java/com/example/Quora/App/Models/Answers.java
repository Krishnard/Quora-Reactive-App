package com.example.Quora.App.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "answers")
public class Answers {
    
    @Id
    private String id;
    
    @NotBlank(message = "Answer content cannot be blank")
    @Size(min = 20, max = 2000, message = "Answer content must be between 20 and 2000 characters")
    private String content;
    
    @Indexed
    private String questionId;
    
    @CreatedDate
    @Indexed
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
}
