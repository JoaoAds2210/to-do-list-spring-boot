package com.example.to_do_spring.controller;
import com.example.to_do_spring.dtos.UserRequest;
import com.example.to_do_spring.dtos.UserResponse;
import com.example.to_do_spring.entity.User;
import com.example.to_do_spring.services.UserServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@Getter
@Setter
public class UserController {

    private final UserServices services;

    //CRUD com HTTP
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request){

        User saved = services.createUser(request);

        UserResponse response = new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getCpf()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        User user = services.findById(id);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getUsername()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> users = services.findAll()
                .stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getCpf()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id]")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        services.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        User updated = services.updateUser(id, request);

        UserResponse response = new UserResponse(
                updated.getId(),
                updated.getUsername(),
                updated.getCpf()
        );

        return ResponseEntity.ok(response);
    }
}
