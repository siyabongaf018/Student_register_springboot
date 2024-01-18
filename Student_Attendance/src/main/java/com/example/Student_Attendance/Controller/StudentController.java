package com.example.Student_Attendance.Controller;


import com.example.Student_Attendance.DTO.AttendanceDTO;
import com.example.Student_Attendance.DTO.CaptureAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentRegisterDTO;
import com.example.Student_Attendance.Entity.Student;
import com.example.Student_Attendance.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentAttendanceDTO>> getAllStudents() {
        List<StudentAttendanceDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody StudentRegisterDTO studentRegisterDTO) {
        Student student = studentService.registerStudent(studentRegisterDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }



    @PostMapping("/capture-attendance")
    public ResponseEntity<String> captureAttendance(@RequestBody List<CaptureAttendanceDTO> studentAttendanceList) {
         studentService.captureAttendance(studentAttendanceList);
        return new ResponseEntity<>("Attendance captured successfully",HttpStatus.OK);

    }
}
