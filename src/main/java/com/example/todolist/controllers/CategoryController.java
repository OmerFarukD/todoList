package com.example.todolist.controllers;

import com.example.todolist.dtos.categories.CategoryAddDto;
import com.example.todolist.dtos.categories.CategoryResponseDto;
import com.example.todolist.services.categories.CategoryService;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
@RequiredArgsConstructor
public final class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ReturnModel<NoDataDto>> add(@RequestBody CategoryAddDto dto){

        var result = this.categoryService.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @GetMapping("/getall")
    public ResponseEntity<ReturnModel<List<CategoryResponseDto>>> getAll(){
        var result = this.categoryService.getAll();
        return ResponseEntity.ok(result);
    }
}
