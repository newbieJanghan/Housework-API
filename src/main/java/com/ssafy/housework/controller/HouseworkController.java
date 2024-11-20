package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.web.interceptor.annotations.Admin;
import com.ssafy.housework.model.housework.HouseworkService;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houseworks")
public class HouseworkController {

    private final HouseworkService houseworkAdminService;

    public HouseworkController(HouseworkService houseworkAdminService) {
        this.houseworkAdminService = houseworkAdminService;
    }

    @Admin
    @GetMapping("/{id}")
    public Housework getHousework(@PathVariable int id) {
        return houseworkAdminService.getOne(id);
    }

    @Admin
    @GetMapping
    public List<Housework> getAllHouseworks() {
        return houseworkAdminService.getAll();
    }

    @Admin
    @PostMapping
    public Housework createHousework(@RequestBody HouseworkCreate houseworkCreate) {
        return houseworkAdminService.create(houseworkCreate);
    }

    @Admin
    @PutMapping("/{id}")
    public Housework updateHousework(@PathVariable int id, @RequestBody HouseworkUpdate houseworkUpdate) {
        return houseworkAdminService.update(id, houseworkUpdate);
    }

    @Admin
    @DeleteMapping("/{id}")
    public void deleteHousework(@PathVariable int id) {
        houseworkAdminService.delete(id);
    }
}