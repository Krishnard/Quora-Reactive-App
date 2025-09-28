package com.example.Quora.App.Repository;

import com.example.Quora.App.Models.Answers;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LikeRepository extends ReactiveMongoRepository<Answers,String> {
}
