package com.digipay.usermanagement.controller;

import com.digipay.usermanagement.model.dto.PermissionDto;
import com.digipay.usermanagement.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public PermissionDto create(@RequestBody PermissionDto permissionDto) {
        return permissionService.save(permissionDto);
    }

    @GetMapping
    public Set<PermissionDto> findAll() {
        return permissionService.findAll();
    }

    @GetMapping("/{id}")
    public PermissionDto findById(@PathVariable Long id) {
        return permissionService.findById(id);
    }

    @PutMapping("/{id}")
    public PermissionDto update(@RequestBody PermissionDto permissionDto,@PathVariable Long id) {
        return permissionService.update(permissionDto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        permissionService.delete(id);
    }
}