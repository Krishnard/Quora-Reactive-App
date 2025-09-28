package com.example.Quora.App.Repository;

import com.example.Quora.App.Models.Answers;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answers,String> {



}
