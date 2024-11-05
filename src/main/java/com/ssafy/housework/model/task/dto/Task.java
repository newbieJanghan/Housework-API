package com.ssafy.housework.model.task.dto;

import java.time.LocalDateTime;

public class Task {
    int id;
    int familyId;
    Integer houseworkId; // nullable for raw text input on column 'name'
    String name;
    String description;
    LocalDateTime dueAt;

    public Task() {
    }

    // with houseworkId
    public Task(int familyId, Integer houseworkId, LocalDateTime dueAt) {
        this.familyId = familyId;
        this.houseworkId = houseworkId;
        this.dueAt = dueAt;
    }

    // without houseworkId
    public Task(int familyId, String name, String description, LocalDateTime dueAt) {
        this.familyId = familyId;
        this.name = name;
        this.description = description;
        this.dueAt = dueAt;
    }

    // for mapper
    public Task(int id, int familyId, int houseworkId, String name, String description, LocalDateTime dueAt) {
        this.id = id;
        this.familyId = familyId;
        this.houseworkId = houseworkId;
        this.name = name;
        this.description = description;
        this.dueAt = dueAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }
}