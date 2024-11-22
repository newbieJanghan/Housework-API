package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.interceptor.annotations.Admin;
import com.ssafy.housework.core.auth.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.interceptor.dto.CurrentUser;
import com.ssafy.housework.core.auth.interceptor.resolvers.RequestUser;
import com.ssafy.housework.model.exceptions.BadRequestException;
import com.ssafy.housework.model.user.UserService;
import com.ssafy.housework.model.user.dto.User;
import com.ssafy.housework.model.user.dto.UserInfo;
import com.ssafy.housework.model.user.dto.UserInfoUpdate;
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

    @Admin
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @Admin
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        if (user.getId() != id) {
            throw new BadRequestException("Invalid userId");
        }
        return userService.update(user);
    }

    @Admin
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.delete(id);
    }


    @Authenticate
    @GetMapping("/my")
    public UserInfo getUser(@RequestUser CurrentUser user) {
        return userService.getUserInfo(user.id());
    }

    @Authenticate
    @GetMapping("/my-family")
    public List<UserInfo> getFamilyUsers(@RequestUser CurrentUser user) {
        return userService.getFamilyMembers(user.familyId());
    }

    @Authenticate
    @PatchMapping("/my")
    public UserInfo updateUserInfo(@RequestUser CurrentUser user, @RequestBody UserInfoUpdate userInfoUpdate) {
        return userService.updateUserInfo(user.id(), userInfoUpdate);
    }
}