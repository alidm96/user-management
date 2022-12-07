package com.digipay.usermanagement.repository;

import com.digipay.usermanagement.model.entity.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
}