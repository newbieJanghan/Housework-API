package com.ssafy.housework.model.familyHousework;

import com.ssafy.housework.model.familyHousework.dto.FamilyHouseworkQuery;
import com.ssafy.housework.model.housework.dto.Housework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FamilyHouseworkDao {

    Housework selectOne(@Param("familyId") int familyId, @Param("id") int id);

    List<Housework> selectAll(int familyId);

    List<Housework> query(int familyId, FamilyHouseworkQuery query);

    int delete(@Param("familyId") int familyId, @Param("id") int id);
}