package com.example.todolist.services.categories;

import com.example.todolist.dtos.categories.CategoryAddDto;
import com.example.todolist.dtos.categories.CategoryResponseDto;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;

import java.util.List;

public sealed interface CategoryService permits CategoryManager {
    ReturnModel<NoDataDto> add(CategoryAddDto categoryAddDto);
    ReturnModel<List<CategoryResponseDto>> getAll();


}
