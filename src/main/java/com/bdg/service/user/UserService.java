package com.bdg.service.user;

import com.bdg.common.exception.RoleNotFoundException;
import com.bdg.common.exception.UserNotFoundException;
import com.bdg.entity.role.Role;
import com.bdg.entity.user.User;
import com.bdg.repository.role.RoleRepository;
import com.bdg.repository.user.UserRepository;
import com.bdg.transform.request.user.UserCreateRequest;
import com.bdg.transform.request.user.UserUpdateRequest;
import com.bdg.transform.response.user.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements CRUDService<UserCreateRequest, UserUpdateRequest, UserResponse, Long> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponse add(UserCreateRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        Role role=roleRepository.findByRoleType(userRequest.getRoleType()).
                                orElseThrow(()->new RoleNotFoundException(userRequest.getRoleType()));
        user.setRoles(List.of(role));
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(savedUser, userResponse);
        userResponse.setRoles(savedUser.getRoles().stream().
                map(Role::getRoleType).
                collect(Collectors.toList()));
        return userResponse;
    }

    @Override
    public UserResponse get(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        userResponse.setRoles(user.getRoles().stream().
                map(Role::getRoleType).
                collect(Collectors.toList()));
        return userResponse;
    }

    @Override
    public void delete(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse update(Long id, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));
        BeanUtils.copyProperties(updateRequest, user);
        User updatedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(updatedUser, userResponse);
        userResponse.setRoles(updatedUser.getRoles().stream().
                map(Role::getRoleType).
                collect(Collectors.toList()));
        return userResponse;
    }
}
