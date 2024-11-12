package com.ssafy.housework.model.workoutCampaign;

import com.ssafy.housework.model.workoutCampaign.dto.WorkoutCampaign;
import com.ssafy.housework.model.workoutCampaign.dto.WorkoutCampaignSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkoutCampaignDao {
    WorkoutCampaign selectOne(int id);

    List<WorkoutCampaign> selectByFamilyMemberId(int familyMemberId);

    List<WorkoutCampaign> search(WorkoutCampaignSearch campaignSearch);

    int insert(WorkoutCampaign workoutCampaign);

    int update(WorkoutCampaign workoutCampaign);

    int delete(int id);
}
