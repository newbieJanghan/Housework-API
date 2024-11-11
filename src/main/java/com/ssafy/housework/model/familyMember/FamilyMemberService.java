package com.ssafy.housework.model.familyMember;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.familyMember.dto.FamilyMember;
import com.ssafy.housework.model.familyMember.dto.FamilyMemberCreate;
import com.ssafy.housework.model.familyMember.dto.FamilyMemberUpdate;
import com.ssafy.housework.model.user.UserDao;
import com.ssafy.housework.model.user.dto.User;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberService {
    private final FamilyMemberDao familyMemberDao;
    private final UserDao userDao;

    public FamilyMemberService(FamilyMemberDao familyMemberDao, UserDao userDao) {
        this.familyMemberDao = familyMemberDao;
        this.userDao = userDao;
    }

    public FamilyMember getOne(int id) {
        FamilyMember familyMember = familyMemberDao.selectOne(id);
        if (familyMember == null) {
            throw new ResourceNotFoundException("Family member not found with id: " + id);
        }

        return familyMember;
    }

    public List<FamilyMember> getAll() {
        return familyMemberDao.selectAll();
    }

    public FamilyMember create(FamilyMemberCreate create) {
        User user = userDao.selectOne(create.userId());
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + create.userId());
        }

        FamilyMember familyMember = new FamilyMember(create.userId(), create.familyId(), create.role());

        int result = familyMemberDao.insert(familyMember);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create family member");
        }

        return familyMember;
    }

    public FamilyMember update(int id, FamilyMemberUpdate update) {
        FamilyMember familyMember = familyMemberDao.selectOne(id);
        if (familyMember == null) {
            throw new ResourceNotFoundException("Family member not found with id: " + id);
        }

        familyMember.setId(id);
        if (update.role() != null) familyMember.setRole(update.role());

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
