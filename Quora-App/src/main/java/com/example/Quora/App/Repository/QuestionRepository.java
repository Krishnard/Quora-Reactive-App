package com.example.Quora.App.Repository;

import com.example.Quora.App.Models.Questions;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Questions,String>{
    
    
    /*
    Mono and Flux are the two primary publishers in Project Reactor used within Spring WebFlux
     */
    
    // Flux<Questions> findByAuthorId(String authorId);
    /*
    Flux - Represents a stream of 0 to N items, which can be processed asynchronously.
    Represents a publisher that emits zero to N elements.
    Used when you expect multiple items to be returned, such as a list of questions by an author.
     */
    
    // Mono<Long> countByAuthorId(String authorId);
    /*
    Mono - Represents a single asynchronous value or an error. (zero or one item)
    Used when you expect a single item or no item at all, such as counting the number of questions by an author.
     */
    

}
