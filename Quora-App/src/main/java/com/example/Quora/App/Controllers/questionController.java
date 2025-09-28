package com.example.Quora.App.Controllers;

import com.example.Quora.App.DTO.QuestionRequestDTO;
import com.example.Quora.App.DTO.QuestionResponseDTO;
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
    
    
    // Offset based pagination
    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestions(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        return questionService.searchQuestions(query, page, size);
    }
    
    
    // Cursor based pagination
    @GetMapping("/search-cursor")
    public Flux<QuestionResponseDTO> searchQuestionsCursor(
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") int size) {
        
        // Implement cursor-based pagination logic here
        // This is a placeholder implementation
        return questionService.searchQuestionsCursor(cursor, size);
    }
    
    
//    @GetMapping("/tag/{tag}")
//    public Flux<QuestionResponseDTO> searchByTag(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size){
//        return questionService.searchByTag(page, size);
//    }
    
}
