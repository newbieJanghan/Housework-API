package com.ssafy.housework.model.task.dto;

import com.ssafy.housework.model.familyMember.dto.FamilyMember;
import com.ssafy.housework.model.housework.dto.Housework;

import java.time.LocalDateTime;

public class Task {
    int id;
    int familyId;
    Integer familyMemberId; // Nullable
    int houseworkId;
    LocalDateTime dueAt;
    LocalDateTime doneAt;

    FamilyMember familyMember;
    Housework housework;

    public Task() {
    }

    public Task(int familyId, int houseworkId, Integer familyMemberId, LocalDateTime dueAt) {
        this.familyId = familyId;
        this.houseworkId = houseworkId;
        this.familyMemberId = familyMemberId;
        this.dueAt = dueAt;
    }

    public Task(int id, Integer familyMemberId, int familyId, int houseworkId, LocalDateTime dueAt, LocalDateTime doneAt, FamilyMember familyMember, Housework housework) {
        this.id = id;
        this.familyMemberId = familyMemberId;
        this.familyId = familyId;
        this.houseworkId = houseworkId;
        this.dueAt = dueAt;
        this.doneAt = doneAt;
        this.familyMember = familyMember;
        this.housework = housework;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
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

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public Housework getHousework() {
        return housework;
    }

    public void setHousework(Housework housework) {
        this.housework = housework;
    }
}