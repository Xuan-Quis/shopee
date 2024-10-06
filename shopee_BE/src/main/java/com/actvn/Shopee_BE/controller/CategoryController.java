package com.actvn.Shopee_BE.controller;

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
    public ApiResponse getCategories(){
        return categoryService.getAllCategories();
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
