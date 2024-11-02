package com.ssafy.housework.model.familyMember;

import com.ssafy.housework.model.familyMember.dto.FamilyMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyMemberDao {
    FamilyMember selectOne(int id);
    List<FamilyMember> selectByFamilyId(int familyId);
    List<FamilyMember> selectAll();
    int insert(FamilyMember familyMember);
    int update(FamilyMember familyMember);
    int delete(int id);
}
