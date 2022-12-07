
package com.digipay.usermanagement.service;

import com.digipay.usermanagement.model.dto.UserDto;
import com.digipay.usermanagement.model.entity.User;
import com.digipay.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements CrudService<UserDto, Long> {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setRoleId(user.getRole().getId());
        userDto.setNationalId(user.getNationalId());
        return userDto;
    }

    private User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setNationalId(userDto.getNationalId());
        user.setRole(roleService.convertDtoToEntity(roleService.findById(userDto.getRoleId())));
        return user;
    }

    @Override
    public Set<UserDto> findAll() {
        Set<UserDto> usersDto = new HashSet<>();
        for (User user : userRepository.findAll()) {
            usersDto.add(convertEntityToDto(user));
        }
        return usersDto;
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertEntityToDto).orElse(null);
    }

    @Override
    public UserDto save(UserDto userDto) {
        userRepository.save(convertDtoToEntity(userDto));
        return userDto;
    }
    @Override
    public UserDto update(UserDto userDto, Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setName(userDto.getName());
            user.get().setNationalId(userDto.getNationalId());
            user.get().setRole(roleService.convertDtoToEntity(roleService.findById(userDto.getRoleId())));
            userRepository.save(user.get());

            return userDto;
        } else return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}