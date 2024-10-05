package com.actvn.Shopee_BE.mapper;

import com.actvn.Shopee_BE.dto.response.CategoryResponse;
import com.actvn.Shopee_BE.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {
    // Category to DTO
    public CategoryResponse mapCategoryToDto(Category entity){

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId(entity.getId());
        categoryResponse.setName(entity.getName());

        return categoryResponse;
    }
}
