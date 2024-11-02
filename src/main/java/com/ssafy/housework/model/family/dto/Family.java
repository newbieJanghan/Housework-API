package com.ssafy.housework.model.family.dto;

import com.ssafy.housework.model.familyMember.dto.FamilyMember;
import com.ssafy.housework.model.task.dto.Task;

import java.util.List;

public class Family {
    int id;
    String name;
    String description;
    List<FamilyMember> members;
    List<Task> tasks;

    public Family() {
    }

    public Family(String name) {
        this.name = name;
    }

    public Family(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Family(int id, String name, String description, List<FamilyMember> members , List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.members = members;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<FamilyMember> getMembers() {
        return members;
    }

    public void setMembers(List<FamilyMember> members) {
        this.members = members;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}