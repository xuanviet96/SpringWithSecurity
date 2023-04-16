package com.example.springwithsecurity.controller;

import com.example.springwithsecurity.entity.Brand;
import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.model.dto.ApiResponse;
import com.example.springwithsecurity.model.dto.Pagination;
import com.example.springwithsecurity.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.springwithsecurity.config.Contant.LIMIT_BRAND;

@RestController
public class BrandController {
    @Autowired
    BrandService brandService;
    @GetMapping(value = "/api/admin/brands")
    public ResponseEntity<Object> getListBrandsPages(@RequestParam(defaultValue = "", required = false) String id,
                                                   @RequestParam(defaultValue = "", required = false) String name,
                                                   @RequestParam(defaultValue = "", required = false) String status,
                                                   @RequestParam(defaultValue = "1", required = false) Integer page,
                                                   @RequestParam(defaultValue = "created_at", required = false) String sortField,
                                                   @RequestParam(defaultValue = "ASC", required = false) Sort.Direction sortDirection){
        Page<Brand> brands = brandService.adminGetListBrands(id, name, status, page, sortField, sortDirection);
        Pagination pagination;
        pagination = new Pagination((int) brands.getTotalElements(), LIMIT_BRAND, page, brands.getTotalPages());
        ApiResponse response = new ApiResponse("success", pagination, brands.getContent());
        return ResponseEntity.ok(response);
    }

}
