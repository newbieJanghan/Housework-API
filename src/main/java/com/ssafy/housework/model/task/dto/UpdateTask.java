package com.ssafy.housework.model.task.dto;

import java.time.LocalDateTime;

public class UpdateTask {
    Integer familyMemberId; // Nullable
    Integer houseworkId; // Nullable
    LocalDateTime dueAt;

    public UpdateTask() {
    }

    public UpdateTask(Integer familyMemberId, Integer houseworkId, LocalDateTime dueAt) {
        this.familyMemberId = familyMemberId;
        this.houseworkId = houseworkId;
        this.dueAt = dueAt;
    }

    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public Integer getHouseworkId() {
        return houseworkId;
    }

    public void setHouseworkId(Integer houseworkId) {
        this.houseworkId = houseworkId;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }
}
