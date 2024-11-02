package com.ssafy.housework.model.familyMember.dto;

import com.ssafy.housework.model.family.dto.Family;
import com.ssafy.housework.model.task.dto.Task;
import com.ssafy.housework.model.user.dto.User;

import java.util.List;

public class FamilyMember {
    int id;
    int userId;
    int familyId;
    String role;

    User user;
    Family family;
    List<Task> tasks;

    public FamilyMember() {
    }

    public FamilyMember(int userId, int familyId, String role) {
        this.userId = userId;
        this.familyId = familyId;
        this.role = role;
    }

    public FamilyMember(int id, int userId, int familyId, String role, User user, Family family,  List<Task> tasks) {
        this.id = id;
        this.userId = userId;
        this.familyId = familyId;
        this.role = role;
        this.user = user;
        this.family = family;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
