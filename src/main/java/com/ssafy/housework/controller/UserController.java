package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Admin;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.web.resolvers.CurrentUser;
import com.ssafy.housework.model.user.UserService;
import com.ssafy.housework.model.user.dto.User;
import com.ssafy.housework.model.user.dto.UserCreate;
import com.ssafy.housework.model.user.dto.UserUpdate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Authenticate
    @GetMapping("/{id}")
    public User getUser(@CurrentUser AuthUser user, @PathVariable int id) {
        if (!user.isAdmin() && user.id() != id) {
            throw new IllegalArgumentException("You are not allowed to access this user");
        }

        return userService.getOne(id);
    }

    @Admin
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @Admin
    @PostMapping
    public User createUser(@RequestBody UserCreate userCreate) {
        return userService.create(userCreate);
    }

    @Admin
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody UserUpdate userUpdate) {
        return userService.update(id, userUpdate);
    }

    @Admin
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.delete(id);
    }
}