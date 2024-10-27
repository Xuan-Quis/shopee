package com.actvn.Shopee_BE.service.impl;

import com.actvn.Shopee_BE.common.Constants;
import com.actvn.Shopee_BE.dto.request.CategoryRequest;
import com.actvn.Shopee_BE.dto.response.CategoryResponse;
import com.actvn.Shopee_BE.exception.NotFoundException;
import com.actvn.Shopee_BE.mapper.EntityDtoMapper;
import com.actvn.Shopee_BE.repository.CategoryRepository;
import com.actvn.Shopee_BE.service.CategoryService;
import com.actvn.Shopee_BE.dto.response.ApiResponse;
import com.actvn.Shopee_BE.dto.response.CategoryItemResponse;
import com.actvn.Shopee_BE.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityDtoMapper mapper;

    @Override
    public ApiResponse getAllCategories(int pageNumber, int pageSize, String sortBy, String sortOrder ) {
        Sort sortByAndOrder = sortOrder.equals(Constants.SORT_BY_ORDER)
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sortByAndOrder);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryPage.getContent();


        List<CategoryItemResponse> list = categories.stream()
                .map(mapper::mapCategoryToDto)
                .collect(Collectors.toList());


        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategories(list);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());
        categoryResponse.setFirstPage(categoryPage.isFirst());
        return ApiResponse
                .builder()
                .message("Get all categories successfully")
                .body(categoryResponse)
                .status(HttpStatus.OK)
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
        Category foundCategory = findCategoryById(id);

        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Get category with "+id +" successfully")
                .body(mapper.mapCategoryToDto(foundCategory))
                .build();
    }

    @Override
    public ApiResponse updateCategory(CategoryRequest categoryRequest, String id) {
        Category category = findCategoryById(id);
        category.setName(categoryRequest.getName());
        Category updated = categoryRepository.save(category);
        return ApiResponse.builder()
                .message("Successfully update")
                .build();
    }
    @Override
    public ApiResponse deleteCategory(String id) {
        categoryRepository.delete(findCategoryById(id));
        return ApiResponse.builder()
                .message("Successfully deleted Category")
                .status(HttpStatus.OK)
                .build();
    }

    private Category findCategoryById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with "+id+" not found"));
        return category;
    }


}
