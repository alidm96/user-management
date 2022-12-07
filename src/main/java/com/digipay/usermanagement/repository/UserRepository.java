package com.digipay.usermanagement.repository;

import com.digipay.usermanagement.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}