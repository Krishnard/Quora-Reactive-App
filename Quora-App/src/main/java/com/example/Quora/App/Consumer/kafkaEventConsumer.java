package com.example.Quora.App.Consumer;

import com.example.Quora.App.Config.kafkaConfig;
import com.example.Quora.App.Events.ViewCountEvent;
import com.example.Quora.App.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class kafkaEventConsumer {
    
    private final QuestionRepository questionRepository;
    
    @KafkaListener(
            topics = kafkaConfig.TOPIC_NAME,
            groupId = "view_count_consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    
    public void handleViewCountEvent(ViewCountEvent viewCountEvent){
        questionRepository.findById(viewCountEvent.getTargetId())
                .flatMap(question -> {
                    question.setViews(question.getViews() + 1);
                    return questionRepository.save(question);
                })
                .subscribe(updatedQuestion -> {
                        System.out.println("Updated view count for question ID " + updatedQuestion.getId());
                        }, error -> {
                    System.out.println("Error updating view count: " + error.getMessage());
                });
    }
}
