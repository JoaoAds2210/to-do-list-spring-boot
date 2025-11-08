package com.example.to_do_spring.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest { //o que o sistema recebe do usuario em forma de JSON

    // Garante que o campo description não seja nulo e nem uma string vazia
    @NotBlank(message = "A descrição da tarefa é obrigatória.")
    String description;

    boolean concluido;
}

