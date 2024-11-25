package com.ssafy.housework.model.housework;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
import com.ssafy.housework.model.housework.dto.HouseworkQuery;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HouseworkService {
    private final HouseworkDao houseworkDao;

    public HouseworkService(HouseworkDao houseworkDao) {
        this.houseworkDao = houseworkDao;
    }

    public Housework getOne(int familyId, int id) {
        Housework housework = houseworkDao.selectOneOfFamily(familyId, id);
        if (housework == null) {
            throw new ResourceNotFoundException("Housework not found with id: " + id);
        }

        return housework;
    }

    public List<Housework> query(HouseworkQuery houseworkQuery) {
        return houseworkDao.queryOfFamily(houseworkQuery);
    }

    public Housework create(int familyId, int registerUserId, HouseworkCreate houseworkCreate) {
        Housework housework = houseworkCreate.toHousework(familyId, registerUserId);
        int result = houseworkDao.insert(housework);
        if (result == 0) {
            throw new RuntimeException("Failed to create housework");
        }

        return houseworkDao.selectOne(housework.getId());
    }

    public Housework update(int familyId, int id, HouseworkUpdate houseworkUpdate) {
        Housework housework = this.getOne(familyId, id);
        houseworkUpdate.setHousework(housework);

        int result = houseworkDao.update(housework);
        if (result == 0) {
            throw new ResourceNotFoundException("Failed to update housework with id: " + id);
        }

        return houseworkDao.selectOne(id);
    }

    public Housework complete(int familyId, int id) {
        Housework housework = this.getOne(familyId, id);
        housework.setDoneAt(LocalDateTime.now());

        int result = houseworkDao.update(housework);
        if (result == 0) {
            throw new ResourceNotFoundException("Failed to update housework with id: " + id);
        }

        return houseworkDao.selectOne(id);
    }

    public Housework ongoing(int familyId, int id) {
        Housework housework = this.getOne(familyId, id);
        housework.setDoneAt(null);

        int result = houseworkDao.update(housework);
        if (result == 0) {
            throw new ResourceNotFoundException("Failed to update housework with id: " + id);
        }

        return houseworkDao.selectOne(id);
    }

    public void delete(int familyId, int id) {
        int result = houseworkDao.deleteOfFamily(familyId, id);
        if (result == 0) {
            throw new ResourceNotFoundException("Failed to delete housework with id: " + id);
        }
    }
}