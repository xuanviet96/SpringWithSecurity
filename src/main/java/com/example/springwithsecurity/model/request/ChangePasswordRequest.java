package com.example.springwithsecurity.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangePasswordRequest {
    @NotBlank(message = "Mật khẩu cũ trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải chứa từ 4 - 20 ký tự")
    @JsonProperty("old_password")
    private String oldPassword;

    @NotBlank(message = "Mật khẩu mới trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải chứa từ 4 - 20 ký tự")
    @JsonProperty("new_password")
    private String newPassword;
}
