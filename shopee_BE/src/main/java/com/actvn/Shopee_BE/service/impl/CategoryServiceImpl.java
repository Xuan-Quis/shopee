package com.actvn.Shopee_BE.service.impl;

import com.actvn.Shopee_BE.dto.request.CategoryRequest;
import com.actvn.Shopee_BE.mapper.EntityDtoMapper;
import com.actvn.Shopee_BE.repository.CategoryRepository;
import com.actvn.Shopee_BE.service.CategoryService;
import com.actvn.Shopee_BE.dto.response.ApiResponse;
import com.actvn.Shopee_BE.dto.response.CategoryResponse;
import com.actvn.Shopee_BE.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityDtoMapper mapper;

    @Override
    public ApiResponse getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> list = categories.stream()
                .map(mapper::mapCategoryToDto)
                .collect(Collectors.toList());

        return ApiResponse
                .builder()
                .message("Get all categories successfully")
                .status(HttpStatus.OK)
                .body(list)
                .build();
    }

    @Override
    public ApiResponse createNewCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        Category created = categoryRepository.save(category);
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Category created successfully")
                .body( mapper.mapCategoryToDto(category))
                .build();
    }

    @Override
    public ApiResponse getCategoryById(String id) {
        Category foundCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with "+id+" not found"));

        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Get category with "+id +" successfully")
                .body(mapper.mapCategoryToDto(foundCategory))
                .build();
    }

    @Override
    public ApiResponse updateCategory(CategoryRequest categoryRequest, String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with "+id+" not found"));
        category.setName(categoryRequest.getName());
        Category updated = categoryRepository.save(category);

        return ApiResponse.builder()
                .message("Successfully update")
                .build();
    }
    @Override
    public ApiResponse deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with "+id+" not found"));
        categoryRepository.delete(category);
        return ApiResponse.builder()
                .message("Successfully deleted Category")
                .status(HttpStatus.OK)
                .build();
    }


}
