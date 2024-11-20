package com.ssafy.housework.model.family;

import com.ssafy.housework.model.family.dto.Family;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyDao {
    Family selectOne(int id);

    List<Family> selectAll();

    int insert(Family family);

    int update(Family family);

    int delete(int id);
}