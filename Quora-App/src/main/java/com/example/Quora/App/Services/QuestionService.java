package com.example.Quora.App.Services;

import com.example.Quora.App.DTO.QuestionRequestDTO;
import com.example.Quora.App.DTO.QuestionResponseDTO;
import com.example.Quora.App.Events.ViewCountEvent;
import com.example.Quora.App.Mapper.QuestionAdapter;
import com.example.Quora.App.Models.Questions;
import com.example.Quora.App.Producers.kafkaEventProducer;
import com.example.Quora.App.Repository.QuestionRepository;
import com.example.Quora.App.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{
    
    private final QuestionRepository questionRepository;
    
    private final kafkaEventProducer kafkaEventProducer;
    
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
    
    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int page, int size) {
        return questionRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTerm, PageRequest.of(page, size))
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error searching questions: " + error))
                .doOnComplete(() -> System.out.println("Search completed successfully."));
    }
    
    
    @Override
    public Flux<QuestionResponseDTO> searchQuestionsCursor(String cursor, int size) {
    
        // In this we don't have to use page number - in short we don't want to have offset
        
        // Here we don't care about page , you can assume page number is 0
        Pageable pageable = PageRequest.of(0, size);
        
        if (!CursorUtils.isValidCursor(cursor)) {
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnError(error -> System.out.println("Error in initial cursor-based search: " + error))
                    .doOnComplete(() -> System.out.println("Initial cursor-based search completed successfully."));
        } else {
            LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp, pageable)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnError(error -> System.out.println("Error in cursor-based search: " + error))
                    .doOnComplete(() -> System.out.println("Cursor-based search completed successfully."));
        }
    }
    
    
//    @Override
//    public Flux<QuestionResponseDTO> searchByTag(int page, int size){
//        // Placeholder implementation
//        return questionRepository.findAllByTagsContaining(PageRequest.of(page, size))
//                .map(QuestionAdapter::toQuestionResponseDTO)
//                .doOnError(error -> System.out.println("Error searching by tag: " + error))
//                .doOnComplete(() -> System.out.println("Search by tag completed successfully."));
//    }
    
    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error retrieving question by ID: " + error))
                .doOnSuccess(response ->{
                    System.out.println("Retrieved question successfully: " + response);
                    ViewCountEvent viewCountEvent = new ViewCountEvent(id, "QUESTION",LocalDateTime.now());
                    kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                });
    }
    
    
}
