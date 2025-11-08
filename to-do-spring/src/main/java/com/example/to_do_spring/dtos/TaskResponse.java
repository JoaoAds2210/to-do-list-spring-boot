package com.example.to_do_spring.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Long id;
    private String description;
    private boolean concluido;

}
