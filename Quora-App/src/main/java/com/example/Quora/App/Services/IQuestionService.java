package com.example.Quora.App.Services;

import com.example.Quora.App.DTO.QuestionRequestDTO;
import com.example.Quora.App.DTO.QuestionResponseDTO;
import com.example.Quora.App.Models.Questions;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
}
