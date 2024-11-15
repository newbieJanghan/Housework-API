package com.ssafy.housework.controller;

import com.ssafy.housework.model.housework.HouseworkService;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houseworks")
public class HouseworkController {

    private final HouseworkService houseworkService;

    public HouseworkController(HouseworkService houseworkService) {
        this.houseworkService = houseworkService;
    }

    @GetMapping("/{id}")
    public Housework getHousework(@PathVariable int id) {
        return houseworkService.getOne(id);
    }

    @GetMapping
    public List<Housework> getAllHouseworks() {
        return houseworkService.getAll();
    }

    @PostMapping
    public Housework createHousework(@RequestBody HouseworkCreate houseworkCreate) {
        return houseworkService.create(houseworkCreate);
    }

    @PutMapping("/{id}")
    public Housework updateHousework(@PathVariable int id, @RequestBody HouseworkUpdate houseworkUpdate) {
        return houseworkService.update(id, houseworkUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteHousework(@PathVariable int id) {
        houseworkService.delete(id);
    }
}