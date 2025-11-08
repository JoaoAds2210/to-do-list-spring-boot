package com.example.to_do_spring.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "O nome é obrigatório.")
    private String username;

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "CPF inválido.")
    private String cpf;
}
