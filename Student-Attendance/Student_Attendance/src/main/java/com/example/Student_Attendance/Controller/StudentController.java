package com.example.Student_Attendance.Controller;


import com.example.Student_Attendance.DTO.AttendanceDTO;
import com.example.Student_Attendance.DTO.StudentAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentDTO;
import com.example.Student_Attendance.Entity.Student;
import com.example.Student_Attendance.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody StudentDTO studentDTO) {
        Student student = studentService.registerStudent(studentDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("/capture-attendance")
    public ResponseEntity<Void> captureAttendance(@RequestBody List<StudentAttendanceDTO> studentAttendanceList) {
        studentService.captureAttendance(studentAttendanceList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
