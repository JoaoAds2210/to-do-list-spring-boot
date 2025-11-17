package com.example.to_do_spring.dtos;

public record UserResponse(
        Long id,
        String username,
        String cpf
) {

    // Construtor com username e cpf
    public UserResponse(String username, String cpf) {
        this(null, username, cpf);
    }

    // Construtor com id e username
    public UserResponse(Long id, String username) {
        this(id, username, null);
    }
}
