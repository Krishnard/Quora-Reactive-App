package com.example.Quora.App.Services;

import com.example.Quora.App.DTO.QuestionRequestDTO;
import com.example.Quora.App.DTO.QuestionResponseDTO;
import com.example.Quora.App.Mapper.QuestionAdapter;
import com.example.Quora.App.Models.Questions;
import com.example.Quora.App.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{
    
    private final QuestionRepository questionRepository;
    
    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        
        Questions question = Questions.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        
        return questionRepository.save(question)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(response -> System.out.println("Question saved successfully: " + response))
                .doOnError(error -> System.out.println("Error creating question: " + error));
                
    }
    

}
