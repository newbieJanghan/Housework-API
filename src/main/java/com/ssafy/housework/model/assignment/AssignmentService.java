package com.ssafy.housework.model.assignment;

import com.ssafy.housework.model.assignment.dto.Assignment;
import com.ssafy.housework.model.assignment.dto.AssignmentCreate;
import com.ssafy.housework.model.assignment.dto.AssignmentUpdate;
import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    private final AssignmentDao assignmentDao;

    public AssignmentService(AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public List<Assignment> getAllByFamilyMemberId(int familyMemberId) {
        return assignmentDao.selectAllByFamilyMemberId(familyMemberId);
    }

    public Assignment getOne(int id) {
        Assignment assignment = assignmentDao.selectOne(id);
        if (assignment == null) {
            throw new ResourceNotFoundException("Assignment not found with id: " + id);
        }

        return assignment;
    }

    public Assignment create(AssignmentCreate create) {
        Assignment assignment = new Assignment(create.familyMemberId(), create.taskId(), create.calorieBurned());

        int result = assignmentDao.insert(assignment);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create assignment");
        }

        return assignment;
    }

    public Assignment update(int id, AssignmentUpdate update) {
        Assignment assignment = assignmentDao.selectOne(id);
        if (assignment == null) {
            throw new ResourceNotFoundException("Assignment not found with id: " + id);
        }

        assignment.setId(id);
        if (update.calorieBurned() != null) assignment.setCalorieBurned(update.calorieBurned());
        if (update.isActive() != null) assignment.setActive(update.isActive());

        int result = assignmentDao.update(assignment);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update assignment");
        }

        return assignment;
    }

    public void delete(int id) {
        int result = assignmentDao.delete(id);
        if (result == 0) {
            throw new ResourceNotFoundException("Assignment not found with id: " + id);
        }
    }

}
