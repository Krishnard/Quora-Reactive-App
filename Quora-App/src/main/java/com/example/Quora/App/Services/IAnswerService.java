package com.example.Quora.App.Services;

import com.example.Quora.App.DTO.AnswerRequestDTO;
import com.example.Quora.App.DTO.AnswerResponseDTO;
import com.example.Quora.App.Models.Answers;
import reactor.core.publisher.Mono;

public interface IAnswerService {
    
    public Mono<AnswerResponseDTO> createAnswers(AnswerRequestDTO answerRequestDTO);
    
    public Mono<AnswerResponseDTO> getAnswerById(String id);


}
