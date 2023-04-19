package com.example.springwithsecurity.service;

import com.example.springwithsecurity.entity.Brand;
import com.example.springwithsecurity.model.request.CreateBrandRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BrandService {
    Page<Brand> adminGetListBrands(String id, String name, String status, Integer page, String sortField, Sort.Direction sortDirection);
    Brand createBrand(CreateBrandRequest createBrandRequest);

}
