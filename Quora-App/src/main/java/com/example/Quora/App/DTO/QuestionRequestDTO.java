package com.example.Quora.App.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDTO {
    
    @NotBlank(message = "Title question cannot be blank")
    @Size(min = 10, max = 200, message = "Title question must be between 10 and 200 characters")
    private String title;
    
    @NotBlank(message = "Content cannot be blank")
    @Size(min = 20, max = 1000, message = "Content must be at least 20 characters long")
    private String content;
}
