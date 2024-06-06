package com.example.todolist.services.categories;

import com.example.todolist.dtos.categories.CategoryAddDto;
import com.example.todolist.dtos.categories.CategoryResponseDto;
import com.example.todolist.entities.Category;
import com.example.todolist.repository.categories.CategoryRepository;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class CategoryManager implements  CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    public ReturnModel<NoDataDto> add(CategoryAddDto categoryAddDto) {
        Category category = CategoryAddDto.convertToEntity(categoryAddDto);
        this.categoryRepository.save(category);

        return ReturnModel.success("Kategori eklendi");
    }

    @Override
    public ReturnModel<List<CategoryResponseDto>> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryResponseDto> dtos = categories
                .stream()
                .map(CategoryResponseDto::convertToResponseDto)
                .toList();

        return ReturnModel.success(dtos,"Kategoriler Listelendi.");
    }
}
