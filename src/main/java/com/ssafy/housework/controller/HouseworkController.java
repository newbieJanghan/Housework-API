package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.interceptor.annotations.Admin;
import com.ssafy.housework.model.housework.HouseworkAdminService;
import com.ssafy.housework.model.housework.dto.Housework;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Housework", description = "가사를 관리하는 API")
@RestController
@RequestMapping("/houseworks")
public class HouseworkController {

    private final HouseworkAdminService houseworkAdminService;

    public HouseworkController(HouseworkAdminService houseworkAdminService) {
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
    public Housework createHousework(@RequestBody Housework housework) {
        return houseworkAdminService.create(housework);
    }

    @Admin
    @PutMapping("/{id}")
    public Housework updateHousework(@PathVariable int id, @RequestBody Housework housework) {
        return houseworkAdminService.update(id, housework);
    }

    @Admin
    @DeleteMapping("/{id}")
    public void deleteHousework(@PathVariable int id) {
        houseworkAdminService.delete(id);
    }
}