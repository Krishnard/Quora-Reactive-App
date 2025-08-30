package com.example.Quora.App.Controllers;

import com.example.Quora.App.DTO.QuestionRequestDTO;
import com.example.Quora.App.DTO.QuestionResponseDTO;
import com.example.Quora.App.Models.Questions;
import com.example.Quora.App.Services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class questionController {
    
    private final IQuestionService questionService;
    
    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
    
        return questionService.createQuestion(questionRequestDTO)
                .doOnError(error -> System.out.println("Error in controller: " + error))
                .doOnSuccess(response -> System.out.println("Controller received response: " + response));
        
    
    }
    
}
