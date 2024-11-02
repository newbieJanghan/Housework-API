package com.ssafy.housework.model.user;

import com.ssafy.housework.model.user.dto.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDao {
    User selectOne(int id);
    List<User> selectAll();
    int insert(User familyMember);
    int update(User familyMember);
    int delete(int id);
}