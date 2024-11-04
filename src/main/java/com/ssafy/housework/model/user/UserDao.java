package com.ssafy.housework.model.user;

import com.ssafy.housework.model.user.dto.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDao {
    User selectOne(int id);
    List<User> selectAll();
    User selectByEmail(String email);
    int insert(User user);
    int update(User user);
    int delete(int id);
}