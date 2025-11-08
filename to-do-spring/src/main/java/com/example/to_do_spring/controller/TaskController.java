package com.example.to_do_spring.controller;

import com.example.to_do_spring.entity.Task;
import com.example.to_do_spring.services.TaskServices;
import com.example.to_do_spring.dtos.TaskRequest;
import com.example.to_do_spring.dtos.TaskResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskServices services;

    // CREATE
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setConcluido(request.isConcluido());

        Task savedTask = services.createTask(task);

        TaskResponse response = new TaskResponse(
                savedTask.getId(),
                savedTask.getDescription(),
                savedTask.isConcluido()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        Task task = services.findById(id);
        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getDescription(),
                task.isConcluido()
        );
        return ResponseEntity.ok(response);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        List<TaskResponse> tasks = services.findAll()
                .stream()
                .map(t -> new TaskResponse(t.getId(), t.getDescription(), t.isConcluido()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        Task updatedTask = services.updateTask(id, request);

        TaskResponse response = new TaskResponse(
                updatedTask.getId(),
                updatedTask.getDescription(),
                updatedTask.isConcluido()
        );

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        services.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}