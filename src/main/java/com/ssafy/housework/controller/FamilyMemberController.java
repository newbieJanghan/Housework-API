package com.ssafy.housework.controller;

import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import com.ssafy.housework.model.familyMember.FamilyMemberService;
import com.ssafy.housework.model.familyMember.dto.CreateFamilyMember;
import com.ssafy.housework.model.familyMember.dto.FamilyMember;
import com.ssafy.housework.model.familyMember.dto.UpdateFamilyMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/family-members")
public class FamilyMemberController {
    private final FamilyMemberService familyMemberService;

    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyMember> getFamilyMember(@PathVariable("id") int id) {
        try {
            FamilyMember familyMember = familyMemberService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(familyMember);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<FamilyMember>> getAllFamilyMembers() {
        List<FamilyMember> familyMembers = familyMemberService.getAll();
        if (familyMembers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(familyMembers);
    }

    @PostMapping
    public ResponseEntity<FamilyMember> createFamilyMember(@RequestBody CreateFamilyMember createFamilyMember) {
        try {
            FamilyMember familyMember = familyMemberService.create(createFamilyMember);
            return ResponseEntity.status(HttpStatus.CREATED).body(familyMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilyMember> updateFamilyMember(@PathVariable("id") int id, @RequestBody UpdateFamilyMember updateFamilyMember) {
        try {
            FamilyMember familyMember = familyMemberService.update(id, updateFamilyMember);
            return ResponseEntity.status(HttpStatus.OK).body(familyMember);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FamilyMember> deleteFamilyMember(@PathVariable("id") int id) {
        try {
            familyMemberService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
