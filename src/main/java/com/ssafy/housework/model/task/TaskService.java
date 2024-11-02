package com.ssafy.housework.model.task;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.task.dto.CreateTask;
import com.ssafy.housework.model.task.dto.Task;
import com.ssafy.housework.model.task.dto.UpdateTask;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public Task getOne(int id) {
        return taskDao.selectOne(id);
    }

    public List<Task> getAll() {
        return taskDao.selectAll();
    }

    public Task create(CreateTask createTask) {
        Task task = new Task(createTask.getFamilyId(), createTask.getHouseworkId(), createTask.getFamilyMemberId(), createTask.getDueAt());

        int result = taskDao.insert(task);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create task");
        }

        return task;
    }

    public Task update(int id, UpdateTask updateTask) {
        Task task = taskDao.selectOne(id);
        if (task == null) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }

        task.setId(id);

        if (updateTask.getFamilyMemberId() != null) {
            task.setFamilyMemberId(updateTask.getFamilyMemberId());
        }

        if (updateTask.getHouseworkId() != null) {
            task.setHouseworkId(updateTask.getHouseworkId());
        }

        if (updateTask.getDueAt() != null) {
            task.setDueAt(updateTask.getDueAt());
        }

        int result = taskDao.update(task);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update task with id: " + id);
        }

        return taskDao.selectOne(id);
    }

    public Task finishTask(int id) {
        Task task = taskDao.selectOne(id);
        if (task == null) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }

        task.setDoneAt(LocalDateTime.now());

        int result = taskDao.update(task);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update task with id: " + id);
        }

        return taskDao.selectOne(id);
    }

    public void delete(int id) {
        int result = taskDao.delete(id);
        if (result == 0) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
    }
}
