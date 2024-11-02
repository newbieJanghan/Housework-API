package com.ssafy.housework.model.family.dto;

import com.ssafy.housework.model.familyMember.dto.FamilyMember;
import com.ssafy.housework.model.task.dto.Task;

import java.util.List;

public class Family {
    int id;
    String name;
    String desc;
    List<FamilyMember> members;
    List<Task> tasks;

    public Family() {
    }

    public Family(String name) {
        this.name = name;
    }

    public Family(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Family(int id, String name, String desc, List<FamilyMember> members ,List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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