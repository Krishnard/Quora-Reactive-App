package com.example.Quora.App.Producers;

import com.example.Quora.App.Events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import com.example.Quora.App.Config.kafkaConfig;

@Service
@RequiredArgsConstructor
public class kafkaEventProducer {
    
    private final KafkaTemplate<String,Object> kafkaTemplate;
    
    public void publishViewCountEvent(ViewCountEvent viewCountEvent){
        kafkaTemplate.send(kafkaConfig.TOPIC_NAME,viewCountEvent.getTargetId(),viewCountEvent)
                .whenComplete((result, err) -> {
                    if (err != null) {
                        System.out.println("Error publishing event: " + err.getMessage());
                    }
                }
        );
    }
}
