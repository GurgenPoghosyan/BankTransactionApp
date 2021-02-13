package com.bdg.endpoints.user;

import com.bdg.entity.user.User;
import com.bdg.service.user.UserService;
import com.bdg.transform.request.user.UserCreateRequest;
import com.bdg.transform.request.user.UserUpdateRequest;
import com.bdg.transform.response.user.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse addUser(@Valid @RequestBody UserCreateRequest addRequest){
        return userService.add(addRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest updateRequest){
        return userService.update(id,updateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
