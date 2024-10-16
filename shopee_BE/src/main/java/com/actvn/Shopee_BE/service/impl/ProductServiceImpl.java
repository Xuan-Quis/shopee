package com.actvn.Shopee_BE.service.impl;

import com.actvn.Shopee_BE.dto.request.ProductRequest;
import com.actvn.Shopee_BE.entity.Category;
import com.actvn.Shopee_BE.entity.Product;
import com.actvn.Shopee_BE.exception.NotFoundException;
import com.actvn.Shopee_BE.repository.CategoryRepository;
import com.actvn.Shopee_BE.repository.ProductRepository;
import com.actvn.Shopee_BE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Product createNewProduct(ProductRequest productRequest, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->{
            throw new NotFoundException("Not found category with id: " + categoryId);
        });
        double price = productRequest.getPrice()
                - (productRequest.getDiscount() * 0.01)*productRequest.getPrice();
        productRequest.setPrice(price);

        Product product = new Product();
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscount(productRequest.getDiscount());
        product.setDescription(productRequest.getDescription());
        product.setImage(productRequest.getImage());
        product.setQuantity(productRequest.getQuantity());

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts(String categoryId) {
        List<Product> products = productRepository.findAll();
        List<Product> filteredProducts = products.stream().filter(
                (s) ->{
                    return s.getCategory().getId().equals(categoryId);
                }
        ).toList();

//        return productRepository.findAllByCategoryId(categoryId);
        return products;
    }
}
