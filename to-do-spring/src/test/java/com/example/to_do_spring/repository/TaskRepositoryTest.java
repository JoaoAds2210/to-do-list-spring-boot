package com.example.to_do_spring.repository;

import com.example.to_do_spring.dtos.TaskRequest;
import com.example.to_do_spring.entity.Task;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class TaskRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void findById() {

    }

    @Test
    void deleteById() {
    }

    private Task createTask(TaskRequest task){
        Task newTask = new Task(task);
        this.entityManager.persist(newTask);
        return newTask;
    }
}