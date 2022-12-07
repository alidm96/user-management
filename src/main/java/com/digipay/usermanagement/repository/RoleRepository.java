package com.digipay.usermanagement.repository;

import com.digipay.usermanagement.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}