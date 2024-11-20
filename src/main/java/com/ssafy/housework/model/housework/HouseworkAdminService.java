package com.ssafy.housework.model.housework;

import com.ssafy.housework.model.housework.dto.Housework;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseworkAdminService {
    private final HouseworkDao houseworkDao;

    public HouseworkAdminService(HouseworkDao houseworkDao) {
        this.houseworkDao = houseworkDao;
    }

    public Housework getOne(int id) {
        Housework housework = houseworkDao.selectOne(id);
        if (housework == null) {
            throw new IllegalArgumentException("Housework not found with id: " + id);
        }

        return housework;
    }

    public List<Housework> getAll() {
        return houseworkDao.selectAll();
    }

    public Housework create(Housework housework) {
        int result = houseworkDao.insert(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create housework");
        }

        return housework;
    }

    public Housework update(int id, Housework housework) {
        if (houseworkDao.selectOne(id) == null) {
            throw new IllegalArgumentException("Housework not found with id: " + id);
        }

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