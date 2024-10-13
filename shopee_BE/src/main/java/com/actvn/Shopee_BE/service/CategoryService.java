package com.actvn.Shopee_BE.service;

import com.actvn.Shopee_BE.dto.request.CategoryRequest;
import com.actvn.Shopee_BE.dto.response.ApiResponse;

public interface CategoryService {
    ApiResponse getAllCategories(int pageNumber, int pageSize, String sortBy, String sortOrder );
    ApiResponse createNewCategory(CategoryRequest categoryRequest);

    ApiResponse getCategoryById(String id);
    ApiResponse updateCategory(CategoryRequest categoryRequest, String id);

    ApiResponse deleteCategory(String id);
}
