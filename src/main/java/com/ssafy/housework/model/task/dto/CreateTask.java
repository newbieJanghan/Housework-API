package com.ssafy.housework.model.task.dto;

import java.time.LocalDateTime;

public class CreateTask {
    int familyId;
    int houseworkId;
    Integer familyMemberId;
    LocalDateTime dueAt;

    public CreateTask() {
    }

    public CreateTask(int familyId, int houseworkId, Integer familyMemberId, LocalDateTime dueAt) {
        this.familyId = familyId;
        this.houseworkId = houseworkId;
        this.familyMemberId = familyMemberId;
        this.dueAt = dueAt;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public int getHouseworkId() {
        return houseworkId;
    }

    public void setHouseworkId(int houseworkId) {
        this.houseworkId = houseworkId;
    }

    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }
}
