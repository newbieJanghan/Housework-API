package com.ssafy.housework.controller;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.web.resolvers.CurrentUser;
import com.ssafy.housework.model.housework.HouseworkService;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import com.ssafy.housework.model.utils.DateQuery;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/families/{familyId}/houseworks")
public class FamilyHouseworkController {
    private final HouseworkService houseworkService;


    public FamilyHouseworkController(HouseworkService houseworkService) {
        this.houseworkService = houseworkService;
    }

    @Authenticate
    @GetMapping("/{id}")
    public Housework getHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @PathVariable int id) {
        validateCurrentUser(user, familyId);

        return houseworkService.getOne(familyId, id);
    }

    @Authenticate
    @GetMapping
    public List<Housework> getAllHouseworks(@CurrentUser AuthUser user, @PathVariable int familyId, @RequestParam FamilyHouseworkQueryParams queryParams) {
        validateCurrentUser(user, familyId);

        return houseworkService.query(familyId, queryParams.assignedUserId, queryParams.getDateQuery());
    }

    @Authenticate
    @PostMapping
    public Housework createHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @RequestBody HouseworkCreate houseworkCreate) {
        validateCurrentUser(user, familyId);

        return houseworkService.create(familyId, user.id(), houseworkCreate);
    }

    @Authenticate
    @PatchMapping("/{id}")
    public Housework updateHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @PathVariable int id, @RequestBody HouseworkUpdate houseworkUpdate) {
        validateCurrentUser(user, familyId);

        return houseworkService.update(familyId, id, houseworkUpdate);
    }

    @Authenticate
    @PatchMapping("/{id}/complete")
    public Housework completeHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @PathVariable int id) {
        validateCurrentUser(user, familyId);

        return houseworkService.complete(familyId, id);
    }

    @Authenticate
    @DeleteMapping("/{id}")
    public void deleteHousework(@CurrentUser AuthUser user, @PathVariable int familyId, @PathVariable int id) {
        validateCurrentUser(user, familyId);

        houseworkService.delete(familyId, id);
    }

    private void validateCurrentUser(AuthUser user, int familyId) {
        if (user.familyId() != familyId) {
            throw new BadRequestException("You are not a member of this family");
        }
    }

    public static class FamilyHouseworkQueryParams {
        @NotNull
        public LocalDateTime from;
        @NotNull
        public LocalDateTime to;

        @Nullable
        public Integer assignedUserId;

        public DateQuery getDateQuery() {
            return new DateQuery(from, to);
        }
    }
}