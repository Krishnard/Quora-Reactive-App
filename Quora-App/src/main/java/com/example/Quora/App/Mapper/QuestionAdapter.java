package com.example.Quora.App.Mapper;

import com.example.Quora.App.DTO.QuestionResponseDTO;
import com.example.Quora.App.Models.Questions;

public class QuestionAdapter {
    
    public static QuestionResponseDTO toQuestionResponseDTO(Questions question) {
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .content(question.getContent())
                .title(question.getTitle())
                .createdAt(question.getCreatedAt())
                .build();
    }
    
}
