package com.ssafy.housework.model.housework;

import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseworkDao {

    Housework selectOne(int id);

    List<Housework> selectAll();

    int insert(Housework housework);

    int update(Housework housework);

    int delete(int id);

    Housework selectOneOfFamily(@Param("familyId") int familyId, @Param("id") int id);

    List<Housework> queryOfFamily(HouseworkQuery query);

    int deleteOfFamily(@Param("familyId") int familyId, @Param("id") int id);
}