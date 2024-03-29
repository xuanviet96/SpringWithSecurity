package com.example.springwithsecurity.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProfileRequest {
    @Pattern(regexp="(09|03|07|08|05)+([0-9]{8})\\b", message = "Điện thoại không hợp lệ")
    private String phone;

    @NotBlank(message = "Họ tên trống")
    private String fullName;

    private String address;
}
