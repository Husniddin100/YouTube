package com.example.YouTube.repository;

import com.example.YouTube.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {
}
