package com.ssafy.housework.model.familyHousework;

import com.ssafy.housework.model.familyHousework.dto.FamilyHouseworkCreate;
import com.ssafy.housework.model.familyHousework.dto.FamilyHouseworkQuery;
import com.ssafy.housework.model.housework.HouseworkDao;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FamilyHouseworkService {
    private final FamilyHouseworkDao familyHouseworkDao;
    private final HouseworkDao houseworkDao;

    public FamilyHouseworkService(FamilyHouseworkDao familyHouseworkDao, HouseworkDao houseworkDao) {
        this.familyHouseworkDao = familyHouseworkDao;
        this.houseworkDao = houseworkDao;
    }

    public Housework getOne(int familyId, int id) {
        Housework housework = familyHouseworkDao.selectOne(familyId, id);
        if (housework == null) {
            throw new IllegalArgumentException("Housework not found with id: " + id);
        }

        return housework;
    }

    public List<Housework> getAll(int familyId) {
        return familyHouseworkDao.selectAll(familyId);
    }

    public List<Housework> query(int familyId, FamilyHouseworkQuery query) {
        return familyHouseworkDao.query(familyId, query);
    }

    public Housework create(int familyId, int registerUserId, FamilyHouseworkCreate houseworkCreate) {
        Housework housework = houseworkCreate.toHousework(familyId, registerUserId);

        int result = houseworkDao.insert(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create housework");
        }

        return housework;
    }

    public Housework update(int familyId, int id, HouseworkUpdate houseworkUpdate) {
        Housework housework = this.getOne(familyId, id);

        houseworkUpdate.setHousework(housework);

        int result = houseworkDao.update(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update housework with id: " + id);
        }

        return housework;
    }


    public void delete(int familyId, int id) {
        int result = familyHouseworkDao.delete(familyId, id);
        if (result == 0) {
            throw new IllegalArgumentException("Failed to delete housework with id: " + id);
        }
    }

    public Housework complete(int familyId, int id) {
        Housework housework = this.getOne(familyId, id);
        housework.setDoneAt(LocalDateTime.now());

        int result = houseworkDao.update(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update housework with id: " + id);
        }

        return housework;
    }
}