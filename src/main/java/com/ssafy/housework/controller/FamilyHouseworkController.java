package com.ssafy.housework.controller;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.web.resolvers.CurrentUser;
import com.ssafy.housework.model.familyHousework.FamilyHouseworkService;
import com.ssafy.housework.model.familyHousework.dto.FamilyHouseworkCreate;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/families/{familyId}/houseworks")
public class FamilyHouseworkController {

    private final FamilyHouseworkService familyHouseworkService;

    public FamilyHouseworkController(FamilyHouseworkService familyHouseworkService) {
        this.familyHouseworkService = familyHouseworkService;
    }

    @Authenticate
    @GetMapping("/{id}")
    public Housework getHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @PathVariable int id) {
        if (user.familyId() != familyId) {
            throw new BadRequestException("You are not a member of this family");
        }

        return familyHouseworkService.getOne(familyId, id);
    }

    @Authenticate
    @GetMapping
    public List<Housework> getAllHouseworks(@CurrentUser AuthUser user, @PathVariable int familyId) {
        if (user.familyId() != familyId) {
            throw new BadRequestException("You are not a member of this family");
        }
        return familyHouseworkService.getAll(familyId);
    }

    @Authenticate
    @PostMapping
    public Housework createHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @RequestBody FamilyHouseworkCreate familyHouseworkCreate) {
        if (user.familyId() != familyId) {
            throw new BadRequestException("You are not a member of this family");
        }

        return familyHouseworkService.create(familyId, user.id(), familyHouseworkCreate);
    }

    @Authenticate
    @PatchMapping("/{id}")
    public Housework updateHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @PathVariable int id, @RequestBody HouseworkUpdate houseworkUpdate) {
        if (user.familyId() != familyId) {
            throw new BadRequestException("You are not a member of this family");
        }

        return familyHouseworkService.update(familyId, id, houseworkUpdate);
    }

    @Authenticate
    @PatchMapping("/{id}/complete")
    public Housework completeHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @PathVariable int id) {
        if (user.familyId() != familyId) {
            throw new BadRequestException("You are not a member of this family");
        }

        return familyHouseworkService.complete(familyId, id);
    }

    @Authenticate
    @DeleteMapping("/{id}")
    public void deleteHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @PathVariable int id) {
        if (user.familyId() != familyId) {
            throw new BadRequestException("You are not a member of this family");
        }
        familyHouseworkService.delete(familyId, id);
    }
}