package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.annotations.Admin;
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

    @Admin
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getOne(id);
    }

    @Admin
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

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