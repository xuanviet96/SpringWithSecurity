package com.example.springwithsecurity.model.mapper;

import com.example.springwithsecurity.entity.Brand;
import com.example.springwithsecurity.model.dto.BrandDTO;
import com.example.springwithsecurity.model.request.CreateBrandRequest;

public class BrandMapper {
    public static Brand toBrand(CreateBrandRequest createBrandRequest) {
        Brand brand = new Brand();
        brand.setName(createBrandRequest.getName());
        brand.setStatus(createBrandRequest.isStatus());
        brand.setDescription(createBrandRequest.getDescription());
        brand.setThumbnail(createBrandRequest.getThumbnail());
        return brand;
    }
    public static BrandDTO toBrandDTO(Brand brand){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        brandDTO.setDescription(brand.getDescription());
        brandDTO.setThumbnail(brand.getThumbnail());
        brandDTO.setStatus(brand.isStatus());
        return brandDTO;
    }
}
