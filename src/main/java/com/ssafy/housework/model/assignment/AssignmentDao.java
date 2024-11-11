package com.ssafy.housework.model.assignment;

import com.ssafy.housework.model.assignment.dto.Assignment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssignmentDao {
    List<Assignment> selectAllByTaskId(int taskId);

    Assignment selectOne(int id);

    int insert(Assignment assignment);

    int update(Assignment assignment);

    int delete(int id);
}
