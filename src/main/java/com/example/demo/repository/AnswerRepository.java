package com.example.demo.repository;


import com.example.demo.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface defines special methods for working with the entity Answer
 *
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
