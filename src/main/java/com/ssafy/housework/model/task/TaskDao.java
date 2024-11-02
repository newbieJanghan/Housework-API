package com.ssafy.housework.model.task;

import com.ssafy.housework.model.task.dto.Task;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TaskDao {
    Task selectOne(int id);
    List<Task> selectByHouseId(int houseId);
    List<Task> selectAll();
    int insert(Task task);
    int update(Task task);
    int delete(int id);
}