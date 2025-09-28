package com.example.Quora.App.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerRequestDTO {
    
    @NotBlank(message = "Answer content cannot be blank")
    @Size(min = 20, max = 2000, message = "Answer content must be between 20 and 2000 characters")
    private String content;
    
    @NotBlank(message = "Question ID is needed")
    private String questionId;

}
