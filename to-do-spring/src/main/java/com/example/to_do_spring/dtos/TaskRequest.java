package com.example.to_do_spring.dtos;

import jakarta.validation.constraints.NotBlank;


public record TaskRequest (
        // Garante que o campo description não seja nulo e nem uma string vazia
        @NotBlank(message = "A descrição da tarefa é obrigatória.")
        String description,
        boolean concluido
) {}

