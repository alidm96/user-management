package com.digipay.usermanagement.service;

import com.digipay.usermanagement.model.dto.RoleDto;
import com.digipay.usermanagement.model.entity.Permission;
import com.digipay.usermanagement.model.entity.Role;
import com.digipay.usermanagement.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService implements CrudService<RoleDto, Long> {

    private final RoleRepository roleRepository;
    private final PermissionService permissionService;

    public RoleService(RoleRepository roleRepository, PermissionService permissionService) {
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;
    }

    public RoleDto convertEntityToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setTitle(role.getTitle());
        Set<Permission> permissions = role.getPermissions();
        Set<Long> permissionsId = new HashSet<>();
        for (Permission permission : permissions) {
            permissionsId.add(PermissionService.convertEntityToDto(permission).getId());
        }
        roleDto.setPermissionsId(permissionsId);
        return roleDto;
    }

    public Role convertDtoToEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setTitle(roleDto.getTitle());
        role.setId(roleDto.getId());
        Set<Long> permissionsId = roleDto.getPermissionsId();
        Set<Permission> permissions = new HashSet<>();
        for (Long permissionId : permissionsId) {
            permissions.add(PermissionService.convertDtoToEntity(permissionService.findById(permissionId)));
        }
        role.setPermissions(permissions);
        return role;
    }

    @Override
    public Set<RoleDto> findAll() {
        Set<RoleDto> rolesDto = new HashSet<>();
        for (Role role : roleRepository.findAll()) {
            rolesDto.add(convertEntityToDto(role));
        }
        return rolesDto;
    }

    @Override
    public RoleDto findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(this::convertEntityToDto).orElse(null);
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        roleRepository.save(convertDtoToEntity(roleDto));
        return roleDto;
    }

    @Override
    public RoleDto update(RoleDto roleDto, Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()) {
            role.get().setTitle(roleDto.getTitle());
            Set<Permission> permissions = new HashSet<>();
            for (Long permissionId: roleDto.getPermissionsId()) {
                permissions.add(PermissionService.convertDtoToEntity(permissionService.findById(permissionId)));
            }
            role.get().setPermissions(permissions);
            roleRepository.save(role.get());
            return roleDto;
        } else return null;
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}