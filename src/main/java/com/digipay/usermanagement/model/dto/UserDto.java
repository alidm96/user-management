package com.digipay.usermanagement.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private Long nationalId;
    private Long roleId;
}
