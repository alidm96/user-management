package com.digipay.usermanagement.service;

import com.digipay.usermanagement.model.dto.PermissionDto;
import com.digipay.usermanagement.model.entity.Permission;
import com.digipay.usermanagement.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PermissionService implements CrudService<PermissionDto, Long> {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public static PermissionDto convertEntityToDto(Permission permission) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permission.getId());
        permissionDto.setTitle(permission.getTitle());
        return permissionDto;
    }

    public static Permission convertDtoToEntity(PermissionDto permissionDto) {
        Permission permission = new Permission();
        permission.setTitle(permissionDto.getTitle());
        permission.setId(permissionDto.getId());
        return permission;
    }

    @Override
    public Set<PermissionDto> findAll() {
        Set<PermissionDto> permissionsDto = new HashSet<>();
        for (Permission permission : permissionRepository.findAll()) {
            permissionsDto.add(convertEntityToDto(permission));
        }
        return permissionsDto;
    }

    @Override
    public PermissionDto findById(Long id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        return permission.map(PermissionService::convertEntityToDto).orElse(null);
    }

    @Override
    public PermissionDto save(PermissionDto permissionDto) {
        permissionRepository.save(convertDtoToEntity(permissionDto));
        return permissionDto;
    }

    @Override
    public PermissionDto update(PermissionDto permissionDto, Long id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        if (permission.isPresent()) {
            permission.get().setTitle(permissionDto.getTitle());
            permissionRepository.save(permission.get());
            return permissionDto;
        } else return null;
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
