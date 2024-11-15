package com.ssafy.housework.controller;

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

    @GetMapping("/{id}")
    public Family getFamily(@PathVariable int id) {
        return familyService.getOne(id);
    }

    @GetMapping
    public List<Family> getAllFamilies() {
        return familyService.getAll();
    }

    @PostMapping
    public Family createFamily(@RequestBody FamilyCreate familyCreate) {
        return familyService.create(familyCreate);
    }

    @PutMapping("/{id}")
    public Family updateFamily(@PathVariable int id, @RequestBody FamilyUpdate familyUpdate) {
        return familyService.update(id, familyUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteFamily(@PathVariable int id) {
        familyService.delete(id);
    }
}