package com.ssafy.housework.model.family;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.family.dto.Family;
import com.ssafy.housework.model.family.dto.FamilyCreate;
import com.ssafy.housework.model.family.dto.FamilyUpdate;
import com.ssafy.housework.model.familyMember.FamilyMemberDao;
import com.ssafy.housework.model.task.TaskDao;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {
    private final FamilyDao familyDao;
    private final FamilyMemberDao familyMemberDao;

    public FamilyService(FamilyDao familyDao, FamilyMemberDao familyMemberDao, TaskDao taskDao) {
        this.familyDao = familyDao;
        this.familyMemberDao = familyMemberDao;
    }

    public Family getOne(int id) {
        Family family = familyDao.selectOne(id);

        if (family == null) {
            throw new ResourceNotFoundException("Family not found with id: " + id);
        }

//        List<FamilyMember> members = familyMemberDao.selectByFamilyId(id);
//        family.setMembers(members);

        return family;
    }

    public List<Family> getAll() {
        return familyDao.selectAll();
    }

    public Family create(FamilyCreate create) throws DataAccessResourceFailureException {
        Family family = new Family(create.name(), create.description());
        int result = familyDao.insert(family);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create family");
        }

        return family;
    }

    public Family update(int id, FamilyUpdate update) {
        Family family = familyDao.selectOne(id);
        if (family == null) {
            throw new ResourceNotFoundException("Family not found with id: " + id);
        }

        family.setId(id);
        if (update.name() != null) family.setName(update.name());
        if (update.description() != null) family.setDescription(update.description());

        int result = familyDao.update(family);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update family with id: " + id);
        }

        return familyDao.selectOne(id);
    }

    public void delete(int id) {
        int result = familyDao.delete(id);
        if (result == 0) {
            throw new ResourceNotFoundException("Family not found with id: " + id);
        }
    }
}