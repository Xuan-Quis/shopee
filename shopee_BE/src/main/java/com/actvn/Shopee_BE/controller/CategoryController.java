package com.actvn.Shopee_BE.controller;

import com.actvn.Shopee_BE.common.Constants;
import com.actvn.Shopee_BE.dto.request.CategoryRequest;
import com.actvn.Shopee_BE.entity.Category;
import com.actvn.Shopee_BE.service.CategoryService;
import com.actvn.Shopee_BE.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/public/categories")
    public ApiResponse getCategories(@RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER, required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                     @RequestParam(value = "sortBy", defaultValue = Constants.CATEGORY_SORT_BY, required = false) String sortBy,
                                     @RequestParam(value = "sortOrder", defaultValue = Constants.SORT_BY_ORDER, required = false) String sortOrder
    ){
        if(pageNumber < 1){
            pageNumber = 1;
        }
        return categoryService.getAllCategories(pageNumber -1, pageSize, sortBy, sortOrder);
    }

    @GetMapping("/public/categories/{id}")
    public ApiResponse getCategories(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }


    @PostMapping("/admin/categories")
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( categoryService.createNewCategory(request));
    }


    @PutMapping("admin/categories/{id}")
    public ResponseEntity updateCategory(@RequestBody CategoryRequest request, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.updateCategory(request,id));
    }

    @DeleteMapping("admin/categories/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.deleteCategory(id));
    }
}
