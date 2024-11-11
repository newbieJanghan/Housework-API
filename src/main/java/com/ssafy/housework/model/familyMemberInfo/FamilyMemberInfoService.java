package com.ssafy.housework.model.familyMemberInfo;

import com.ssafy.housework.model.familyMemberInfo.dto.FamilyMemberInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberInfoService {
    private final FamilyMemberInfoDao familyMemberInfoDao;

    public FamilyMemberInfoService(FamilyMemberInfoDao familyMemberInfoDao) {
        this.familyMemberInfoDao = familyMemberInfoDao;
    }

    public FamilyMemberInfo getFamilyMemberInfo(int familyMemberId) {
        return familyMemberInfoDao.selectOne(familyMemberId);
    }

    public List<FamilyMemberInfo> getFamilyMembers(int familyId) {
        return familyMemberInfoDao.selectFamilyMembers(familyId);
    }
}
