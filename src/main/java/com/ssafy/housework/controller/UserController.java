package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.dto.AuthenticatedUser;
import com.ssafy.housework.core.auth.interceptor.annotations.Admin;
import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.user.UserService;
import com.ssafy.housework.model.user.dto.User;
import com.ssafy.housework.model.user.dto.UserCreate;
import com.ssafy.housework.model.user.dto.UserUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> getUser(@PathVariable int id) {
        try {
            User user = userService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Admin
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestAttribute(AuthenticatedUser.key) AuthenticatedUser user) {
        if (!user.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreate userCreate) {
        try {
            User user = userService.create(userCreate);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Admin
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserUpdate userUpdate) {
        try {
            User user = userService.update(id, userUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Admin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}