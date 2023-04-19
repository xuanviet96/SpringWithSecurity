package com.example.springwithsecurity.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {
    private long id;
    private String name;
    private String description;
    private String thumbnail;
    private boolean status;
}
