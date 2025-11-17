package com.example.to_do_spring.dtos;

public record TaskResponse(
        Long id,
        String description,
        boolean concluido
) {}
