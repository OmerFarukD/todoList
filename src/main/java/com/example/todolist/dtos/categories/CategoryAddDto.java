package com.example.todolist.dtos.categories;

import com.example.todolist.entities.Category;

public record CategoryAddDto(String name) {

    public static Category convertToEntity(CategoryAddDto dto){
        return new Category(dto.name);
    }
}
