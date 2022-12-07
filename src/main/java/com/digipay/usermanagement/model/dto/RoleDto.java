package com.digipay.usermanagement.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {
    private Long id;
    private String title;
    private Set<Long> permissionsId;
}
