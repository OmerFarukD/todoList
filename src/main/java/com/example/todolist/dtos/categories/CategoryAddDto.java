package com.example.todolist.dtos.categories;

import com.example.todolist.entities.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryAddDto(
        @NotNull
        @Size(min = 2)
        String name) {

    public static Category convertToEntity(CategoryAddDto dto){
        return new Category(dto.name);
    }
}
