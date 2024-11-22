package com.ssafy.housework.model.family;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.family.dto.Family;
import com.ssafy.housework.model.family.dto.FamilyUpdate;
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
            throw new ResourceNotFoundException("Family not found with id: " + id);
        }

        return family;
    }

    public List<Family> getAll() {
        return familyDao.selectAll();
    }

    public Family create(Family family) {
        int result = familyDao.insert(family);
        if (result == 0) {
            throw new RuntimeException("Failed to create family");
        }

        return familyDao.selectOne(family.getId());
    }

    public Family update(Family family) {
        int result = familyDao.update(family);
        if (result == 0) {
            throw new ResourceNotFoundException("Failed to update family with id: " + family.getId());
        }

        return familyDao.selectOne(family.getId());
    }

    public Family update(int familyId, FamilyUpdate familyUpdate) {
        Family family = this.getOne(familyId);
        familyUpdate.setFamily(family);

        int result = familyDao.update(family);
        if (result == 0) {
            throw new ResourceNotFoundException("Failed to update family with id: " + familyId);
        }

        return familyDao.selectOne(familyId);
    }

    public void delete(int id) {
        int result = familyDao.delete(id);
        if (result == 0) {
            throw new ResourceNotFoundException("Failed to delete family with id: " + id);
        }
    }
}