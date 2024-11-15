package com.ssafy.housework.model.family;

import com.ssafy.housework.model.family.dto.Family;
import com.ssafy.housework.model.family.dto.FamilyCreate;
import com.ssafy.housework.model.family.dto.FamilyInfo;
import com.ssafy.housework.model.family.dto.FamilyUpdate;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {
    private final FamilyDao familyDao;

    public FamilyService(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    public Family getOne(int id) {
        Family family = familyDao.selectOne(id);

        if (family == null) {
            throw new IllegalArgumentException("Family not found with id: " + id);
        }

        return family;
    }

    public List<Family> getAll() {
        return familyDao.selectAll();
    }

    public FamilyInfo getFamilyInfo(int familyId) {
        Family family = familyDao.selectOneWithUsers(familyId);
        if (family == null) {
            throw new IllegalArgumentException("Family not found with id: " + familyId);
        }

        return family.toFamilyInfo();
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
            throw new IllegalArgumentException("Family not found with id: " + id);
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
            throw new IllegalArgumentException("Family not found with id: " + id);
        }
    }
}