package com.ssafy.housework.model.familyMemberInfo;

import com.ssafy.housework.model.familyMemberInfo.dto.FamilyMemberInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyMemberInfoDao {
    FamilyMemberInfo selectOne(int familyMemberId);

    List<FamilyMemberInfo> selectFamilyMembers(int familyId);

}
