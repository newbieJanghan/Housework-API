package com.ssafy.housework.model.user;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.user.dto.User;
import com.ssafy.housework.model.user.dto.UserCreate;
import com.ssafy.housework.model.user.dto.UserInfo;
import com.ssafy.housework.model.user.dto.UserUpdate;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getOne(int id) {
        User user = userDao.selectOne(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        return user;
    }

    public UserInfo getUserInfo(int id) {
        User user = userDao.selectOne(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        return user.toUserInfo();
    }

    public List<User> getAll() {
        return userDao.selectAll();
    }

    public User create(UserCreate userCreate) {
        User user = new User(userCreate.familyId(), userCreate.name(), userCreate.email(), userCreate.password(), userCreate.profileImageUrl(), userCreate.calorieGoal());

        int result = userDao.insert(user);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create user");
        }

        return user;
    }

    public User update(int id, UserUpdate userUpdate) {
        User user = userDao.selectOne(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        user.setId(id);
        if (userUpdate.name() != null) user.setName(userUpdate.name());
        if (userUpdate.password() != null) user.setPassword(userUpdate.password());
        if (userUpdate.profileImageUrl() != null) user.setProfileImageName(userUpdate.profileImageUrl());
        if (userUpdate.calorieGoal() != null) user.setCalorieGoal(userUpdate.calorieGoal());

        int result = userDao.update(user);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update user with id: " + id);
        }

        return userDao.selectOne(id);
    }

    public void delete(int id) {
        int result = userDao.delete(id);
        if (result == 0) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }
}