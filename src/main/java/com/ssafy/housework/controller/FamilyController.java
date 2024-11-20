package com.ssafy.housework.controller;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Admin;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.web.resolvers.CurrentUser;
import com.ssafy.housework.model.family.FamilyService;
import com.ssafy.housework.model.family.dto.Family;
import com.ssafy.housework.model.family.dto.FamilyCreate;
import com.ssafy.housework.model.family.dto.FamilyUpdate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/families")
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @Authenticate
    @GetMapping("/{id}")
    public Family getFamily(@CurrentUser AuthUser authUser, @PathVariable int id) {
        if (!authUser.isAdmin() && authUser.familyId() != id) {
            throw new BadRequestException("You are not a member of this family");
        }
        return familyService.getOne(id);
    }

    @Admin
    @GetMapping
    public List<Family> getAllFamilies() {
        return familyService.getAll();
    }

    @Admin
    @PostMapping
    public Family createFamily(@RequestBody FamilyCreate familyCreate) {
        return familyService.create(familyCreate);
    }

    @Authenticate
    @PatchMapping("/{id}")
    public Family updateFamily(@CurrentUser AuthUser authUser, @PathVariable int id, @RequestBody FamilyUpdate familyUpdate) {
        if (authUser.familyId() != id) {
            throw new BadRequestException("You are not a member of this family");
        }
        return familyService.update(id, familyUpdate);
    }

    @Admin
    @DeleteMapping("/{id}")
    public void deleteFamily(@PathVariable int id) {
        familyService.delete(id);
    }
}