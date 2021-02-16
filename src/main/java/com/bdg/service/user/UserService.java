package com.bdg.service.user;

import com.bdg.common.exception.RoleNotFoundException;
import com.bdg.common.exception.UserNotFoundException;
import com.bdg.entity.role.Role;
import com.bdg.entity.user.User;
import com.bdg.repository.role.RoleRepository;
import com.bdg.repository.user.UserRepository;
import com.bdg.transform.request.user.UpdateRequest;
import com.bdg.transform.request.user.UserCreateRequest;
import com.bdg.transform.request.user.UserUpdateRequest;
import com.bdg.transform.response.user.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements CRUDService<UserCreateRequest, UpdateRequest, UserResponse, Long> {

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
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(savedUser, userResponse);
        return userResponse;
    }

    @Override
    public UserResponse get(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
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
    public UserResponse update(Long id, UpdateRequest updateRequest) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));
        BeanUtils.copyProperties(updateRequest, user);
        User updatedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(updatedUser, userResponse);
        return userResponse;
    }
}
