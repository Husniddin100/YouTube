package com.example.YouTube.controller;

import com.example.YouTube.dto.CategoryDTO;
import com.example.YouTube.enums.LangEnum;
import com.example.YouTube.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        CategoryDTO dtoList = categoryService.create(dto);
        return ResponseEntity.ok(dtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable("id") Integer id,
                                              @RequestBody CategoryDTO dto,
                                              @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum language) {
        return ResponseEntity.ok(categoryService.update(id, dto,language));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum language) {
        return ResponseEntity.ok(categoryService.deleteId(id,language));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<CategoryDTO>> getAll( @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum language) {

        return ResponseEntity.ok(categoryService.getAll(language));
    }


}



