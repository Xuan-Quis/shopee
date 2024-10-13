package com.actvn.Shopee_BE.repository;

import com.actvn.Shopee_BE.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Override
    Page<Category> findAll(Pageable pageable);
}

