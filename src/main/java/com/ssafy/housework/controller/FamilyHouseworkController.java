package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.web.resolvers.CurrentUser;
import com.ssafy.housework.model.housework.HouseworkService;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/family-houseworks")
public class FamilyHouseworkController {
    private final HouseworkService houseworkService;


    public FamilyHouseworkController(HouseworkService houseworkService) {
        this.houseworkService = houseworkService;
    }

    @Authenticate
    @GetMapping("/{id}")
    public Housework getHousework(@CurrentUser AuthUser user, @PathVariable int id) {
        return houseworkService.getOne(user.familyId(), id);
    }

    @Authenticate
    @GetMapping
    public List<Housework> getAllHouseworks(@CurrentUser AuthUser user, @RequestParam LocalDateTime from, LocalDateTime to, @RequestParam(required = false) Integer assignedUserId) {
        return houseworkService.query(user.familyId(), from, to, assignedUserId);
    }

    @Authenticate
    @PostMapping
    public Housework createHousework(@CurrentUser AuthUser user, @RequestBody HouseworkCreate houseworkCreate) {
        houseworkCreate.validate();
        return houseworkService.create(user.familyId(), user.id(), houseworkCreate);
    }

    @Authenticate
    @PatchMapping("/{id}")
    public Housework updateHousework(@CurrentUser AuthUser user, @PathVariable int id, @RequestBody HouseworkUpdate houseworkUpdate) {
        houseworkUpdate.validate();
        return houseworkService.update(user.familyId(), id, houseworkUpdate);
    }

    @Authenticate
    @PatchMapping("/{id}/complete")
    public Housework completeHousework(@CurrentUser AuthUser user, @PathVariable int id) {
        return houseworkService.complete(user.familyId(), id);
    }

    @Authenticate
    @DeleteMapping("/{id}")
    public void deleteHousework(@CurrentUser AuthUser user, @PathVariable int id) {
        houseworkService.delete(user.familyId(), id);
    }
}