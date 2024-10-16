package com.actvn.Shopee_BE.controller;

import com.actvn.Shopee_BE.common.Constants;
import com.actvn.Shopee_BE.dto.request.ProductRequest;
import com.actvn.Shopee_BE.dto.response.ApiResponse;
import com.actvn.Shopee_BE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController()
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ApiResponse createProduct(@PathVariable("categoryId") String categoryId,
                                     @RequestBody ProductRequest productRequest
                              ){
        return ApiResponse.builder()
                .message("Success create product for category with id: "+ categoryId)
                .body(productService.createNewProduct(productRequest, categoryId))
                .status(HttpStatus.CREATED)
                .build();
    }
    @GetMapping("/admin/categories/{categoryId}/product")
    public ApiResponse getProducts(@PathVariable("categoryId") String categoryId,
                                   @RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER, required = false) Integer pageNumber,
                                   @RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                   @RequestParam(value = "sortBy", defaultValue = Constants.CATEGORY_SORT_BY, required = false) String sortBy,
                                   @RequestParam(value = "sortOrder", defaultValue = Constants.CATEGORY_SORT_BY_ORDER, required = false) String sortOrder
                                   ){

        return ApiResponse.builder()
                .body(productService.getAllProducts(categoryId))
                .build();
    }



}
