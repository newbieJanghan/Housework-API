package com.ssafy.housework.model.user;

import com.ssafy.housework.model.user.dto.User;
import com.ssafy.housework.model.user.dto.UserInfo;
import com.ssafy.housework.model.user.dto.UserInfoUpdate;
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
            throw new IllegalArgumentException("User not found with id: " + id);
        }

        return user;
    }

    public List<User> getAll() {
        return userDao.selectAll();
    }

    public User create(User user) {
        int result = userDao.insert(user);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create user");
        }

        return userDao.selectOne(user.getId());
    }

    public User update(User user) {
        int result = userDao.update(user);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update user with id: " + user.getId());
        }

        return userDao.selectOne(user.getId());
    }

    public UserInfo getUserInfo(int id) {
        User user = userDao.selectOne(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }

        return UserInfo.of(user);
    }

    public List<UserInfo> getFamilyMembers(int familyId) {
        List<User> users = userDao.selectByFamilyId(familyId);

        return UserInfo.of(users);
    }

    public UserInfo updateUserInfo(int id, UserInfoUpdate userInfoUpdate) {
        User user = userDao.selectOne(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }

        userInfoUpdate.setUser(user);

        int result = userDao.update(user);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update user with id: " + id);
        }

        user = userDao.selectOne(id);
        return UserInfo.of(user);
    }

    public void delete(int id) {
        int result = userDao.delete(id);
        if (result == 0) {
            throw new IllegalArgumentException("Failed to delete user with id: " + id);
        }
    }
}