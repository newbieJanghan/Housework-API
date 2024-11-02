package com.ssafy.housework.model.user;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.family.FamilyDao;
import com.ssafy.housework.model.user.dto.CreateUser;
import com.ssafy.housework.model.user.dto.UpdateUser;
import com.ssafy.housework.model.user.dto.User;
import com.ssafy.housework.model.family.dto.Family;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.dao.DataAccessException;
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

    public List<User> getAll() {
        return userDao.selectAll();
    }

    public User create(CreateUser createUser) {
        User user = new User(createUser.getName(), createUser.getEmail(), createUser.getPassword());

        int result = userDao.insert(user);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create user");
        }

        return user;
    }

    public User update(int id, UpdateUser updateUser) {
        User user = userDao.selectOne(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        user.setId(id);
        user.setName(updateUser.getName());

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