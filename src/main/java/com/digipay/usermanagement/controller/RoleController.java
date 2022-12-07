package com.digipay.usermanagement.controller;

import com.digipay.usermanagement.model.dto.RoleDto;
import com.digipay.usermanagement.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public RoleDto create(@RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @GetMapping
    public Set<RoleDto> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleDto findById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PutMapping("/{id}")
    public RoleDto update(@RequestBody RoleDto roleDto, @PathVariable Long id) {
        return roleService.update(roleDto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}