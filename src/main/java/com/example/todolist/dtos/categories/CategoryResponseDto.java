package com.example.todolist.dtos.categories;

import com.example.todolist.entities.Category;

public record CategoryResponseDto(int id, String name) {


    public static CategoryResponseDto convertToResponseDto(Category category){
        return new CategoryResponseDto(category.getId(), category.getName());
    }

}
