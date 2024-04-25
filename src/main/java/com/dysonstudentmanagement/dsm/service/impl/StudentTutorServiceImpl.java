package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.StudentTutorDto;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutorCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.StudentTutorMapper;
import com.dysonstudentmanagement.dsm.repository.StudentTutorRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.StudentTutorService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentTutorServiceImpl implements StudentTutorService {
    private UserPrimaryDataRepository userPrimaryDataRepo;
    private StudentTutorRepository studentTutorRepo;

    @Override
    public StudentTutorDto createStudentTutor(StudentTutorDto studentTutorDto) {
        StudentTutor studentTutor = StudentTutorMapper.mapToStudentTutor(studentTutorDto);

        UserPrimaryData studentData = userPrimaryDataRepo.findById(studentTutor.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StudentID does not exist in foreign key table 'UserPrimaryData'") //TODO: consider making custom @ResponseStatus exception (as with other custom made exception to better describe exception to api caller.
                );

        UserPrimaryData staffData = userPrimaryDataRepo.findById(studentTutor.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StaffID does not exist in foreign key table 'UserPrimaryData'") //TODO: consider making custom @ResponseStatus exception (as with other custom made exception to better describe exception to api caller.
                );

        List<StudentTutor> studentsAssignedToStaff = studentTutorRepo.findByStaffID(studentTutor.getStaffID());
        if(!(studentsAssignedToStaff.size() < 15)){
            throw new ResourceNotFoundException(studentTutor.getStaffID()+ ": Staff member has maximum number of assigned students, cannot assign new student");
        }
        List<StudentTutor > studentHasTutor = studentTutorRepo.findByStudentID(studentTutor.getStudentID());
        if(studentHasTutor.size()>0){ //prevents a student having > 1 StudentTutor record
            throw new DataIntegrityViolationException("Failed...Record already exists for studentID");
        } else{
            studentTutor.setStudentPrimaryData(studentData);
            studentTutor.setStaffPrimaryData(staffData);
            StudentTutor savedData = studentTutorRepo.save(studentTutor);
            return StudentTutorMapper.mapToStudentTutorDto(savedData);
        }
    }

    @Override
    public StudentTutorDto createStudentTutorRandomAssign(String studentID) {
        StudentTutor studentTutor = new StudentTutor();
        studentTutor.setStudentID(studentID);

        List<UserPrimaryData> tutors = userPrimaryDataRepo.findByUserTypeIsNot(UserPrimaryData.UserType.student);
        int numAssignedStudents;
        for(UserPrimaryData tutor:tutors){
            numAssignedStudents = studentTutorRepo.findByStaffID(tutor.getUserID()).size();
            if(numAssignedStudents < 15){
                studentTutor.setStaffID(tutor.getUserID());
                studentTutor.setStaffPrimaryData(tutor);
                break;
            }
        }

        if(studentTutor.getStaffID() == null){
            throw new ResourceNotFoundException("No staff members are available to be assigned a new student");
        }

        UserPrimaryData studentData = userPrimaryDataRepo.findById(studentTutor.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StudentID does not exist in foreign key table 'UserPrimaryData'") //TODO: consider making custom @ResponseStatus exception (as with other custom made exception to better describe exception to api caller.
                );

        studentTutor.setStudentPrimaryData(studentData);

        //Optional<StudentTutor> studentTutorExists = studentTutorRepo.findById(studentTutorCompositeKey);
        //if(studentTutorExists.isPresent()){
        List<StudentTutor > studentHasTutor = studentTutorRepo.findByStudentID(studentTutor.getStudentID());
        if(studentHasTutor.size()>0){ //prevents a student having > 1 StudentTutor record
            throw new DataIntegrityViolationException("Failed...Record already exists for studentID");
        } else {
            StudentTutor savedData = studentTutorRepo.save(studentTutor);
            return StudentTutorMapper.mapToStudentTutorDto(savedData);
        }
    }

    @Override
    public StudentTutorDto getStudentTutor(StudentTutorCompositeKey targetKey) {
        StudentTutor studentTutor = studentTutorRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Student Tutor record not found with primary key provided")
                );
        return StudentTutorMapper.mapToStudentTutorDto(studentTutor);
    }

    @Override
    public List<StudentTutorDto> getStudentTutorByStaffID(String staffID) {
        List<StudentTutor> studentTutors = studentTutorRepo.findByStaffID(staffID);
        return studentTutors.stream().map((studentTutor) -> StudentTutorMapper.mapToStudentTutorDto(studentTutor)).collect(Collectors.toList());
    }

    @Override
    public List<StudentTutorDto> getStudentTutorByStudentID(String studentID) {
        List<StudentTutor> studentTutors = studentTutorRepo.findByStaffID(studentID);
        return studentTutors.stream().map((studentTutor) -> StudentTutorMapper.mapToStudentTutorDto(studentTutor)).collect(Collectors.toList());
    }

    @Override
    public StudentTutorDto updateStudentTutor(StudentTutorCompositeKey targetKey, StudentTutorDto updatedStudentTutorDto) {
        StudentTutor studentTutor = studentTutorRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Student Tutor record not found with primary key provided")
                );

        StudentTutor updatedStudentTutor = StudentTutorMapper.mapToStudentTutor(updatedStudentTutorDto);
        updatedStudentTutor.setStudentID(targetKey.getStudentID());
        UserPrimaryData studentData = userPrimaryDataRepo.findById(studentTutor.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StudentID does not exist in foreign key table 'UserPrimaryData'") //TODO: consider making custom @ResponseStatus exception (as with other custom made exception to better describe exception to api caller.
                );

        studentTutor.setStudentPrimaryData(studentData);

        UserPrimaryData staffData = userPrimaryDataRepo.findById(studentTutor.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StaffID does not exist in foreign key table 'UserPrimaryData'") //TODO: consider making custom @ResponseStatus exception (as with other custom made exception to better describe exception to api caller.
                );

        studentTutor.setStaffPrimaryData(staffData);

        List<StudentTutor> studentsAssignedToStaff = studentTutorRepo.findByStaffID(updatedStudentTutor.getStaffID());
        if(!(studentsAssignedToStaff.size() < 15)){
            throw new ResourceNotFoundException(studentTutor.getStaffID()+ ": Staff member has maximum number of assigned students, cannot assign new student");
        }



        studentTutorRepo.deleteById(targetKey);
        StudentTutor savedData = studentTutorRepo.save(updatedStudentTutor);
        return StudentTutorMapper.mapToStudentTutorDto(savedData);
    }

    @Override
    public void deleteStudentTutor(StudentTutorCompositeKey targetKey) {
        StudentTutor studentTutor = studentTutorRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Student Tutor record not found with primary key provided")
                );
        studentTutorRepo.deleteById(targetKey);
    }
}
