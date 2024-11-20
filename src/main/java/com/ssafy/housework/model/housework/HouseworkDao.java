package com.ssafy.housework.model.housework;

import com.ssafy.housework.model.housework.dto.Housework;
import jakarta.annotation.Nullable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface HouseworkDao {

    Housework selectOne(int id);

    List<Housework> selectAll();

    int insert(Housework housework);

    int update(Housework housework);

    int delete(int id);

    Housework selectOneOfFamily(@Param("familyId") int familyId, @Param("id") int id);

    List<Housework> queryOfFamily(
            @Param("familyId") int familyId,
            @Param("assignedUserId") Integer assignedUserId,
            @Param("from") @Nullable LocalDateTime from,
            @Param("to") @Nullable LocalDateTime to
    );

    int deleteOfFamily(@Param("familyId") int familyId, @Param("id") int id);
}