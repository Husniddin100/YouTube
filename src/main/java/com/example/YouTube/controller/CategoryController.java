package com.example.YouTube.controller;

import com.example.YouTube.dto.CategoryDTO;
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

    @PostMapping("") //   /region
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        CategoryDTO dtoList=categoryService.create(dto);
        return ResponseEntity.ok(dtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updayteId(@PathVariable("id") Integer id,
                                             @RequestBody CategoryDTO dto){

    return ResponseEntity.ok(categoryService.update(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id){

        return ResponseEntity.ok(categoryService.deleteId(id));
    }
    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAll(){

        return ResponseEntity.ok(categoryService.getAll());
    }


}



