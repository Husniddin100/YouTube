package com.example.YouTube.service;

import com.example.YouTube.dto.CategoryDTO;
import com.example.YouTube.entity.CategoryEntity;
import com.example.YouTube.enums.LangEnum;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ResourceBundleService resourceBundleService;
    public CategoryDTO create(CategoryDTO dto){
        CategoryEntity entity=new CategoryEntity();
        entity.setCategoryName(dto.getCategoryName());
        entity.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public Boolean update(Integer id, CategoryDTO dto, LangEnum language){
        Optional<CategoryEntity>optional=categoryRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException(resourceBundleService.getMessage("category.not.found",language));
        }
        CategoryEntity entity=get(id);
        entity.setCategoryName(dto.getCategoryName());
        entity.setCategoryName(dto.getCategoryName());
        categoryRepository.save(entity);
        return true;
    }

    public Boolean deleteId(Integer id, LangEnum language){
        Optional<CategoryEntity> optional=categoryRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException(resourceBundleService.getMessage("dont.delete",language));
        }
        categoryRepository.delete(optional.get());
        return true;
    }
    public List<CategoryDTO> getAll(LangEnum language){
        Iterable<CategoryEntity> entityList=categoryRepository.findAll();
        List<CategoryDTO>dtoList=new LinkedList<>();
        for (CategoryEntity entity:entityList){
            CategoryDTO dto=new CategoryDTO();
            dto.setCategoryName(entity.getCategoryName());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public CategoryEntity get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Region not found");
        });
    }

}
