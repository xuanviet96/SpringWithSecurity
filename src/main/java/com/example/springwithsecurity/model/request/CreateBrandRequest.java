package com.example.springwithsecurity.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
    @NotBlank(message = "Tên nhãn hiệu trống!")
    @Size(max = 50,message = "Tên nhãn hiệu có độ dài tối đa 50 ký tự!")
    private String name;
    private String description;
    private String thumbnail;
    private boolean status;
}
