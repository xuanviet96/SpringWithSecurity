package com.example.springwithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "status",columnDefinition = "BOOLEAN")
    private boolean status;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "modified_at")
    private Timestamp modifiedAt;
}
