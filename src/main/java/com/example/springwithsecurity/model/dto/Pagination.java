package com.example.springwithsecurity.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int total;
    private int perPage;
    private int currentPage;
    private int totalPages;
}
