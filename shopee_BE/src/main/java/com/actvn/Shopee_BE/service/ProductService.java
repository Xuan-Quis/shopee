package com.actvn.Shopee_BE.service;

import com.actvn.Shopee_BE.dto.request.ProductRequest;
import com.actvn.Shopee_BE.dto.response.ApiResponse;
import com.actvn.Shopee_BE.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductService {
    Product createNewProduct(ProductRequest productRequest, String categoryId);
    ApiResponse getAllProductsByCategoryId(String categoryId, int pageNumber, int pageSize, String sortBy, String sortOrder);

    ApiResponse getProductByKeyword(String keyword, int pageNumber, int pageSize, String sortBy, String sortOrder);

    ApiResponse updateProductImage(String productId, MultipartFile image);
}
