package com.bdg.controllers.user;

import com.bdg.service.user.UserService;
import com.bdg.transform.request.user.UserCreateRequest;
import com.bdg.transform.request.user.UserUpdateRequest;
import com.bdg.transform.response.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResponse addUser(@Valid @RequestBody UserCreateRequest addRequest){
        return userService.add(addRequest);
    }

    @GetMapping("/get/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.get(id);
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest updateRequest){
        return userService.update(id,updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
