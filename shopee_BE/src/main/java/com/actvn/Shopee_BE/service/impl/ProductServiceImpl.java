package com.actvn.Shopee_BE.service.impl;

import com.actvn.Shopee_BE.common.Constants;
import com.actvn.Shopee_BE.dto.request.ProductRequest;
import com.actvn.Shopee_BE.dto.response.ApiResponse;
import com.actvn.Shopee_BE.dto.response.CategoryResponse;
import com.actvn.Shopee_BE.dto.response.ProductItemResponse;
import com.actvn.Shopee_BE.dto.response.ProductResponse;
import com.actvn.Shopee_BE.entity.Category;
import com.actvn.Shopee_BE.entity.Product;
import com.actvn.Shopee_BE.exception.NotFoundException;
import com.actvn.Shopee_BE.repository.CategoryRepository;
import com.actvn.Shopee_BE.repository.ProductRepository;
import com.actvn.Shopee_BE.service.FileService;
import com.actvn.Shopee_BE.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Product createNewProduct(ProductRequest productRequest, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->{
            throw new NotFoundException("Not found category with id: " + categoryId);
        });
        double price = productRequest.getPrice()
                - (productRequest.getDiscount() * 0.01)*productRequest.getPrice();
        productRequest.setPrice(price);

        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .category(category)
                .discount(productRequest.getDiscount())
                .price(price)
                .image(productRequest.getImage())
                .quantity(productRequest.getQuantity())
                .specialPrice(productRequest.getSpecialPrice())
                .build();

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public ApiResponse getAllProductsByCategoryId(String categoryId, int pageNumber, int pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equals(Constants.SORT_BY_ORDER)
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sortByAndOrder);

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->{
            throw new NotFoundException("Not found category with id: " + categoryId);
        });

        List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);

        List<ProductItemResponse> list = products.stream().map(product ->
                modelMapper.map(product, ProductItemResponse.class))
                .toList();

        return ApiResponse
                .builder()
                .message("Get all products by category id:"+ categoryId)
                .body(list)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public ApiResponse getProductByKeyword(String keyword, int pageNumber, int pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equals(Constants.SORT_BY_ORDER)
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sortByAndOrder);


        Page<Product> productPage = productRepository.findProductByProductNameLike('%'+keyword+'%', pageable);

        List<Product> products = productPage.getContent();

        List<ProductItemResponse> list = products.stream().map(product ->
                modelMapper.map(product, ProductItemResponse.class))
                .toList();

        ProductResponse categoryResponse = new ProductResponse();
        categoryResponse.setCategories(list);
        categoryResponse.setPageNumber(productPage.getNumber());
        categoryResponse.setPageSize(productPage.getSize());
        categoryResponse.setTotalPages(productPage.getTotalPages());
        categoryResponse.setTotalElements(productPage.getTotalElements());
        categoryResponse.setLastPage(productPage.isLast());
        categoryResponse.setFirstPage(productPage.isFirst());

        products.forEach(System.out::println);
        return ApiResponse
                .builder()
                .message("Get all products have "+ keyword)
                .body(categoryResponse)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public ApiResponse updateProductImage(String productId, MultipartFile image) {
        Product product = findProductById(productId);
        String imagePath;
        try {
            imagePath = fileService.uploadImage(path, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setImage(imagePath);
        productRepository.save(product);
        return ApiResponse.builder()
                .message("Product updated")
                .body(modelMapper.map(product, ProductItemResponse.class))
                .status(HttpStatus.OK)
                .build();
    }

    private Product findProductById(String productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Not found product with id: " + productId)
        );
    }

    private Category findCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->{
            throw new NotFoundException("Not found category with id: " + categoryId);
        });
        return category;
    }
}
