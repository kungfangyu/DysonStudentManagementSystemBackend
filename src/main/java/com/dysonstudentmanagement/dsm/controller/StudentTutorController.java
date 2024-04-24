package com.dysonstudentmanagement.dsm.controller;


import com.dysonstudentmanagement.dsm.dto.StudentTutorDto;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutorCompositeKey;
import com.dysonstudentmanagement.dsm.service.StudentTutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/studentTutor")
public class StudentTutorController {
    private StudentTutorService studentTutorService;

    @PostMapping
    public ResponseEntity<StudentTutorDto> createStudentTutor(@RequestBody StudentTutorDto studentTutorDto){
        StudentTutorDto savedTutor = studentTutorService.createStudentTutor(studentTutorDto);
        return new ResponseEntity<>(savedTutor, HttpStatus.CREATED);
    }

    @PostMapping ("{studentID}")
    public ResponseEntity<StudentTutorDto> createStudentTutorRandomAssign(@PathVariable("studentID") String studentID){
        StudentTutorDto savedTutor = studentTutorService.createStudentTutorRandomAssign(studentID);
        return new ResponseEntity<>(savedTutor, HttpStatus.CREATED);
    }

    @GetMapping("{studentID}/{staffID")
    public ResponseEntity<StudentTutorDto> getStudentTutor(@PathVariable("studentID") String studentID, @PathVariable("staffID") String staffID){
        StudentTutorCompositeKey targetKey = new StudentTutorCompositeKey(studentID,staffID);
        StudentTutorDto studentTutorDto = studentTutorService.getStudentTutor(targetKey);
        return ResponseEntity.ok(studentTutorDto);
    }

    @GetMapping("/by-student/{studentID}")
    public ResponseEntity<List<StudentTutorDto>> getStudentTutorByStudentID(@PathVariable("studentID") String studentID){
        List<StudentTutorDto> studentTutorDtos = studentTutorService.getStudentTutorByStudentID(studentID);
        return ResponseEntity.ok(studentTutorDtos);
    }

    @GetMapping("/by-staff/{staffID}")
    public ResponseEntity<List<StudentTutorDto>> getStudentTutorByStaffID(@PathVariable("staffID") String staffID){
        List<StudentTutorDto> studentTutorDtos = studentTutorService.getStudentTutorByStaffID(staffID);
        return ResponseEntity.ok(studentTutorDtos);
    }

    @PutMapping("{studentID}/{staffID}")
    public ResponseEntity<StudentTutorDto> updateStudentTutor(@PathVariable("studentID") String studentID, @PathVariable("staffID") String staffID, @RequestBody StudentTutorDto updateStudentTutorDto){
        StudentTutorCompositeKey targetKey = new StudentTutorCompositeKey(studentID,staffID);
        StudentTutorDto savedStudentTutor = studentTutorService.updateStudentTutor(targetKey,updateStudentTutorDto);
        return ResponseEntity.ok(savedStudentTutor);
    }

    @DeleteMapping("{studentID}/{staffID}")
    public ResponseEntity<String> deleteStudentTutor(@PathVariable("studentID") String studentID, @PathVariable("staffID") String staffID){
        StudentTutorCompositeKey targetKey = new StudentTutorCompositeKey(studentID,staffID);
        studentTutorService.deleteStudentTutor(targetKey);
        return ResponseEntity.ok("StudentTutor record deleted successfully");
    }
}
