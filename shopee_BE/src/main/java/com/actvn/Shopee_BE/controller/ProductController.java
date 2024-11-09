package com.actvn.Shopee_BE.controller;

import com.actvn.Shopee_BE.common.Constants;
import com.actvn.Shopee_BE.dto.request.ProductRequest;
import com.actvn.Shopee_BE.dto.response.ApiResponse;
import com.actvn.Shopee_BE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/public/categories/{categoryId}/product")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable("categoryId") String categoryId,
                                                @RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                             @RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                                             @RequestParam(value = "sortBy", defaultValue = Constants.CATEGORY_SORT_BY, required = false) String sortBy,
                                                             @RequestParam(value = "sortOrder", defaultValue = Constants.SORT_BY_ORDER, required = false) String sortOrder
                                   ){
        if(pageNumber < 1){
            pageNumber = 1;
        }
        return ResponseEntity.ok(productService.getAllProductsByCategoryId(categoryId, pageNumber, pageSize, sortBy, sortOrder));
    }
    @GetMapping("/public/products/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable("productId") String productId){
        return null;
    }


    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ApiResponse> getProductByKeyword(@PathVariable String keyword,
                                                           @RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                                           @RequestParam(value = "sortBy", defaultValue = Constants.PRODUCT_SORT_BY, required = false) String sortBy,
                                                           @RequestParam(value = "sortOrder", defaultValue = Constants.SORT_BY_ORDER, required = false) String sortOrder
                                                           ){
        if(pageNumber < 1){
            pageNumber = 1;
        }
        return ResponseEntity.status(HttpStatus.OK).
                body(productService.getProductByKeyword(keyword,pageNumber-1,pageSize,sortBy,sortOrder ));

    }

    @PutMapping("/admin/product/{productId}/image")
    public ResponseEntity<ApiResponse> updateProductImage(@PathVariable String productId, @RequestParam("image") MultipartFile image){


        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProductImage(productId, image));
    }

}











