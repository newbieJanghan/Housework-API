package com.ssafy.housework.controller;

import com.ssafy.housework.controller.utils.DateQueryParams;
import com.ssafy.housework.core.auth.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.interceptor.dto.CurrentUser;
import com.ssafy.housework.core.auth.interceptor.resolvers.RequestUser;
import com.ssafy.housework.model.housework.HouseworkService;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
import com.ssafy.housework.model.housework.dto.HouseworkQuery;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Family Housework", description = "가족의 가사를 관리하는 API")
@RestController
@RequestMapping("/family-houseworks")
public class FamilyHouseworkController {
    private final HouseworkService houseworkService;

    public FamilyHouseworkController(HouseworkService houseworkService) {
        this.houseworkService = houseworkService;
    }

    @Authenticate
    @GetMapping("/{id}")
    public Housework getHousework(@RequestUser CurrentUser user, @PathVariable int id) {
        return houseworkService.getOne(user.familyId(), id);
    }

    @Authenticate
    @GetMapping
    public List<Housework> getAllHouseworks(@RequestUser CurrentUser user, @RequestParam(required = false) Integer assignedUserId, DateQueryParams dateQuery) {
        return houseworkService.query(new HouseworkQuery(user.familyId(), assignedUserId, dateQuery.from(), dateQuery.to()));
    }

    @Authenticate
    @PostMapping
    public Housework createHousework(@RequestUser CurrentUser user, @RequestBody HouseworkCreate houseworkCreate) {
        return houseworkService.create(user.familyId(), user.id(), houseworkCreate);
    }

    @Authenticate
    @PatchMapping("/{id}")
    public Housework updateHousework(@RequestUser CurrentUser user, @PathVariable int id, @RequestBody HouseworkUpdate houseworkUpdate) {
        return houseworkService.update(user.familyId(), id, houseworkUpdate);
    }

    @Authenticate
    @PatchMapping("/{id}/complete")
    public Housework completeHousework(@RequestUser CurrentUser user, @PathVariable int id) {
        return houseworkService.complete(user.familyId(), id);
    }

    @Authenticate
    @PatchMapping("/{id}/ongoing")
    public Housework ongoingHousework(@RequestUser CurrentUser user, @PathVariable int id) {
        return houseworkService.ongoing(user.familyId(), id);
    }

    @Authenticate
    @DeleteMapping("/{id}")
    public void deleteHousework(@RequestUser CurrentUser user, @PathVariable int id) {
        houseworkService.delete(user.familyId(), id);
    }
}