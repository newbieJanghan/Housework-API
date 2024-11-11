package com.ssafy.housework.model.task;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.task.dto.Task;
import com.ssafy.housework.model.task.dto.TaskCreate;
import com.ssafy.housework.model.task.dto.TaskUpdate;
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

    public Task create(TaskCreate taskCreate) {
        Task task = new Task(taskCreate.familyId(), taskCreate.houseworkId(), taskCreate.name(), taskCreate.description(), taskCreate.dueAt());

        int result = taskDao.insert(task);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create task");
        }

        return task;
    }

    public Task update(int id, TaskUpdate taskUpdate) {
        Task task = taskDao.selectOne(id);
        if (task == null) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }

        task.setId(id);

        if (taskUpdate.name() != null) task.setName(taskUpdate.name());
        if (taskUpdate.description() != null) task.setDescription(taskUpdate.description());
        if (taskUpdate.dueAt() != null) task.setDueAt(taskUpdate.dueAt());

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
