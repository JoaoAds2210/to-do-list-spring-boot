package com.example.to_do_spring.repository;
import com.example.to_do_spring.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {

}
