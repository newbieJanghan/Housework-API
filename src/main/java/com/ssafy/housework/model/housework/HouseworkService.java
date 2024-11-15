package com.ssafy.housework.model.housework;

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

    public Housework create(HouseworkCreate houseworkCreate) {
        Housework housework = new Housework(
                houseworkCreate.familyId(),
                houseworkCreate.registerUserId(),
                houseworkCreate.assignedUserId(),
                houseworkCreate.name(),
                houseworkCreate.description(),
                houseworkCreate.color(),
                houseworkCreate.calorieAmount(),
                houseworkCreate.startAt(),
                houseworkCreate.dueAt()
        );

        int result = houseworkDao.insert(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create housework");
        }

        return housework;
    }

    public Housework update(int id, HouseworkUpdate houseworkUpdate) {
        Housework housework = houseworkDao.selectOne(id);
        if (housework == null) {
            throw new IllegalArgumentException("Housework not found with id: " + id);
        }

        housework.setId(id);
        if (houseworkUpdate.registerUserId() != null) housework.setRegisterUserId(houseworkUpdate.registerUserId());
        if (houseworkUpdate.assignedUserId() != null) housework.setAssignedUserId(houseworkUpdate.assignedUserId());
        if (houseworkUpdate.name() != null) housework.setName(houseworkUpdate.name());
        if (houseworkUpdate.description() != null) housework.setDescription(houseworkUpdate.description());
        if (houseworkUpdate.color() != null) housework.setColor(houseworkUpdate.color());
        if (houseworkUpdate.calorieAmount() != null) housework.setCalorieAmount(houseworkUpdate.calorieAmount());
        if (houseworkUpdate.startAt() != null) housework.setStartAt(houseworkUpdate.startAt());
        if (houseworkUpdate.dueAt() != null) housework.setDueAt(houseworkUpdate.dueAt());
        if (houseworkUpdate.doneAt() != null) housework.setDoneAt(houseworkUpdate.doneAt());

        int result = houseworkDao.update(housework);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update housework with id: " + id);
        }

        return houseworkDao.selectOne(id);
    }

    public void delete(int id) {
        int result = houseworkDao.delete(id);
        if (result == 0) {
            throw new IllegalArgumentException("Housework not found with id: " + id);
        }
    }

    public void makeDone(Housework housework) {

    }
}