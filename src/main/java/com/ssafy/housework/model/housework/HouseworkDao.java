package com.ssafy.housework.model.housework;

import com.ssafy.housework.model.housework.dto.Housework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseworkDao {

    Housework selectOne(int id);

    Housework selectOneOfFamily(@Param("id") int id, @Param("familyId") int familyId);

    List<Housework> selectAll();

    int insert(Housework housework);

    int update(Housework housework);

    int delete(int id);
}