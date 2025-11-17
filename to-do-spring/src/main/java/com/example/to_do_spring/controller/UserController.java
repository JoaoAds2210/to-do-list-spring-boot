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
        User user = new User();
        user.setUsername(request.getUsername());
        user.setCpf(user.getCpf());

        User saveUser = services.createUser(user);

        UserResponse response = new UserResponse(
                saveUser.getId(),
                saveUser.getUsername()
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        services.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request){
        User user = services.updateUser(id, request);
        user.setUsername(user.getUsername());

        UserResponse response = new UserResponse(
                user.getId(),
                user.getUsername()
        );

        return ResponseEntity.ok(response);
    }
}
