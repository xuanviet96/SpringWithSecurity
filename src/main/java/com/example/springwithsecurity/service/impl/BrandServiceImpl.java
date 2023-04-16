package com.example.springwithsecurity.service.impl;

import com.example.springwithsecurity.entity.Brand;
import com.example.springwithsecurity.repository.BrandRepository;
import com.example.springwithsecurity.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.example.springwithsecurity.config.Contant.LIMIT_BRAND;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Override
    public Page<Brand> adminGetListBrands(String id, String name, String status, Integer page, String sortField, Sort.Direction sortDirection) {
        page --;
        if(page < 0) {
            page = 0;
        }
        Sort sort = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(0, LIMIT_BRAND, sort);
        Page<Brand> brandsPage = brandRepository.adminGetListBrands(id, name, status, pageable);
        int totalPage = brandsPage.getTotalPages();
        if(page > totalPage) {
            pageable = PageRequest.of(totalPage - 1, LIMIT_BRAND, sort);
        } else {
            pageable = PageRequest.of(page, LIMIT_BRAND, sort);
        }
        return brandRepository.adminGetListBrands(id, name, status, pageable);
    }
}
