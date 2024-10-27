package com.actvn.Shopee_BE.repository;

import com.actvn.Shopee_BE.entity.Category;
import com.actvn.Shopee_BE.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p from Product p where p.category.id like :categoryId ")
    List<Product> findAllByCategoryId(@Param("categoryId") String categoryId);

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryOrderByPriceAsc(Category category);
    
    List<Product> findProductByProductNameLikeIgnoreCase(String keyword);

    Page<Product> findProductByProductNameLike(String keyword, Pageable pageable);
}
