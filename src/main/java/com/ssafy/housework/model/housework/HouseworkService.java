package com.ssafy.housework.model.housework;

import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
import com.ssafy.housework.model.housework.dto.HouseworkSearch;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseworkService {
    private final HouseworkDao houseworkDao;

    public HouseworkService(HouseworkDao houseworkDao) {
        this.houseworkDao = houseworkDao;
    }

    public Housework getOne(int id) {
        Housework housework = houseworkDao.selectOne(id);
        if (housework == null) {
            throw new IllegalArgumentException("Housework not found with id: " + id);
        }

        return housework;
    }

    public List<Housework> search(int familyId) {
        HouseworkSearch search = new HouseworkSearch(familyId);
        return houseworkDao.search(search);
    }

    public Housework create(HouseworkCreate houseworkCreate) {
        Housework housework = houseworkCreate.toHousework();

        int result = houseworkDao.insert(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create housework");
        }

        return housework;
    }

    public Housework update(int id, HouseworkUpdate houseworkUpdate) {
        Housework housework = this.getOne(id);
        houseworkUpdate.setHousework(housework);

        int result = houseworkDao.update(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update housework with id: " + id);
        }

        return houseworkDao.selectOne(id);
    }


    public void delete(int id) {
        int result = houseworkDao.delete(id);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to delete housework with id: " + id);
        }
    }
}