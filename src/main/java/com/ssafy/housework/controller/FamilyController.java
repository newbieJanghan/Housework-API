package com.ssafy.housework.controller;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Admin;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.web.resolvers.CurrentUser;
import com.ssafy.housework.model.family.FamilyService;
import com.ssafy.housework.model.family.dto.Family;
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

    @Admin
    @GetMapping("/{id}")
    public Family getFamily(@PathVariable int id) {
        return familyService.getOne(id);
    }

    @Admin
    @GetMapping
    public List<Family> getAllFamilies() {
        return familyService.getAll();
    }

    @Admin
    @PostMapping
    public Family createFamily(@RequestBody Family family) {
        return familyService.create(family);
    }

    @Admin
    @PutMapping("/{id}")
    public Family updateFamily(@PathVariable int id, @RequestBody Family family) {
        if (family.getId() != id) {
            throw new BadRequestException("Invalid familyId");
        }
        return familyService.update(family);
    }

    @Admin
    @DeleteMapping("/{id}")
    public void deleteFamily(@PathVariable int id) {
        familyService.delete(id);
    }

    @Authenticate
    @GetMapping("/my")
    public Family getFamilyInfo(@CurrentUser AuthUser user) {
        return familyService.getOne(user.familyId());
    }

    @Authenticate
    @PatchMapping("/my")
    public Family updateFamily(@CurrentUser AuthUser user, @RequestBody FamilyUpdate familyUpdate) {
        return familyService.update(user.familyId(), familyUpdate);
    }
}