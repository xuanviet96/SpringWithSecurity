package com.example.springwithsecurity.repository;

import com.example.springwithsecurity.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String name);
    @Query(value = "SELECT * FROM brand b " +
            "WHERE b.id LIKE CONCAT('%',?1,'%') " +
            "AND b.name LIKE CONCAT('%',?2,'%') " +
            "AND b.status LIKE CONCAT('%',?3,'%')", nativeQuery = true)
    Page<Brand> adminGetListBrands(String id, String name, String status, Pageable pageable);


}
