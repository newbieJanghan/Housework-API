package com.ssafy.housework.model.housework;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
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
        return houseworkDao.selectOne(id);
    }

    public List<Housework> getAll() {
        return houseworkDao.selectAll();
    }

    public Housework create(HouseworkCreate createDto) {
        Housework housework = new Housework(createDto.familyId(), createDto.name(), createDto.calorieAmount());

        int result = houseworkDao.insert(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create housework");
        }

        return housework;
    }

    public Housework update(int id, HouseworkUpdate updateDto) {
        Housework housework = houseworkDao.selectOne(id);
        if (housework == null) {
            throw new ResourceNotFoundException("Housework not found with id: " + id);
        }

        housework.setId(id);
        if (updateDto.name() != null) housework.setName(updateDto.name());
        if (updateDto.calorieAmount() != null) housework.setCalorieAmount(updateDto.calorieAmount());

        int result = houseworkDao.update(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update housework with id: " + id);
        }

        return houseworkDao.selectOne(id);
    }

    public void delete(int id) {
        int result = houseworkDao.delete(id);
        if (result == 0) {
            throw new ResourceNotFoundException("Housework not found with id: " + id);
        }
    }
}