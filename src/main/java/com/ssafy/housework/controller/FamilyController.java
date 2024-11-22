package com.ssafy.housework.controller;

import com.ssafy.housework.controller.exceptions.AmbiguousTargetException;
import com.ssafy.housework.core.auth.interceptor.annotations.Admin;
import com.ssafy.housework.core.auth.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.interceptor.dto.CurrentUser;
import com.ssafy.housework.core.auth.interceptor.resolvers.RequestUser;
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
            throw new AmbiguousTargetException();
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
    public Family getFamilyInfo(@RequestUser CurrentUser user) {
        return familyService.getOne(user.familyId());
    }

    @Authenticate
    @PatchMapping("/my")
    public Family updateFamily(@RequestUser CurrentUser user, @RequestBody FamilyUpdate familyUpdate) {
        return familyService.update(user.familyId(), familyUpdate);
    }
}