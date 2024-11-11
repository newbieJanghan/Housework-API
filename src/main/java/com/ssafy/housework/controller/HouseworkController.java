package com.ssafy.housework.controller;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.housework.HouseworkService;
import com.ssafy.housework.model.housework.dto.Housework;
import com.ssafy.housework.model.housework.dto.HouseworkCreate;
import com.ssafy.housework.model.housework.dto.HouseworkUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/houseworks")
public class HouseworkController {

    private final HouseworkService houseworkService;

    public HouseworkController(HouseworkService houseworkService) {
        this.houseworkService = houseworkService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Housework> getHousework(@PathVariable int id) {
        try {
            Housework housework = houseworkService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(housework);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Housework>> getAllHouseworks() {
        List<Housework> houseworks = houseworkService.getAll();
        if (houseworks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(houseworks);
    }

    @PostMapping
    public ResponseEntity<Housework> createHousework(@RequestBody HouseworkCreate houseworkCreate) {
        try {
            Housework housework = houseworkService.create(houseworkCreate);
            return ResponseEntity.status(HttpStatus.CREATED).body(housework);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Housework> updateHousework(@PathVariable int id, @RequestBody HouseworkUpdate houseworkUpdate) {
        try {
            Housework housework = houseworkService.update(id, houseworkUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(housework);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHousework(@PathVariable int id) {
        try {
            houseworkService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}