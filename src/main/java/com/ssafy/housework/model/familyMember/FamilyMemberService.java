package com.ssafy.housework.model.familyMember;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.family.dto.UpdateFamily;
import com.ssafy.housework.model.familyMember.dto.CreateFamilyMember;
import com.ssafy.housework.model.familyMember.dto.FamilyMember;
import com.ssafy.housework.model.familyMember.dto.UpdateFamilyMember;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberService {
    private final FamilyMemberDao familyMemberDao;

    public FamilyMemberService(FamilyMemberDao familyMemberDao) {
        this.familyMemberDao = familyMemberDao;
    }

    public FamilyMember getOne(int id) {
        return familyMemberDao.selectOne(id);
    }

    public List<FamilyMember> getAll() {
        return familyMemberDao.selectAll();
    }

    public FamilyMember create(CreateFamilyMember create) {
        FamilyMember familyMember = new FamilyMember(create.getUserId(), create.getFamilyId(), create.getRole());

        int result = familyMemberDao.insert(familyMember);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create family member");
        }

        return familyMember;
    }

    public FamilyMember update(int id, UpdateFamilyMember update) {
        FamilyMember familyMember = familyMemberDao.selectOne(id);
        if (familyMember == null) {
            throw new ResourceNotFoundException("Family member not found with id: " + id);
        }

        familyMember.setId(id);
        familyMember.setRole(update.getRole());

        int result = familyMemberDao.update(familyMember);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update family member with id: " + id);
        }

        return familyMemberDao.selectOne(id);
    }

    public void delete(int id) {
        int result = familyMemberDao.delete(id);
        if (result == 0) {
            throw new ResourceNotFoundException("Family member not found with id: " + id);
        }
    }
}
