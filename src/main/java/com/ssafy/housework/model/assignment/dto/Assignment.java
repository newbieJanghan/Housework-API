package com.ssafy.housework.model.assignment.dto;

import java.time.LocalDateTime;

public class Assignment {
    int id;
    int taskId;
    Integer familyMemberId; // nullable
    LocalDateTime doneAt;

    public Assignment() {
    }

    // not assigned
    public Assignment(int taskId, LocalDateTime doneAt) {
        this.taskId = taskId;
        this.doneAt = doneAt;
    }

    // assigned
    public Assignment(int taskId, Integer familyMemberId, LocalDateTime doneAt) {
        this.taskId = taskId;
        this.familyMemberId = familyMemberId;
        this.doneAt = doneAt;
    }

    // for mapper
    public Assignment(int id, int taskId, Integer familyMemberId, LocalDateTime doneAt) {
        this.id = id;
        this.taskId = taskId;
        this.familyMemberId = familyMemberId;
        this.doneAt = doneAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }
}
