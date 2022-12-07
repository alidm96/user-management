package com.digipay.usermanagement.controller;

import com.digipay.usermanagement.model.dto.UserDto;
import com.digipay.usermanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping
    public Set<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable Long id) {
        return userService.update(userDto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}