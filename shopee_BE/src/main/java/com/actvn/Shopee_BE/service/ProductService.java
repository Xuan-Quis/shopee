package com.actvn.Shopee_BE.service;

import com.actvn.Shopee_BE.dto.request.ProductRequest;
import com.actvn.Shopee_BE.entity.Product;
import java.util.List;


public interface ProductService {
    Product createNewProduct(ProductRequest productRequest, String categoryId);
    List<Product> getAllProducts(String categoryId);
}
