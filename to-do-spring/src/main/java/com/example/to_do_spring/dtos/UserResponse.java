package com.example.to_do_spring.dtos;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String username;
    private String cpf;

    public UserResponse(String username, String cpf) {
        this.username = username;
        this.cpf = cpf;
    }

    public UserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
