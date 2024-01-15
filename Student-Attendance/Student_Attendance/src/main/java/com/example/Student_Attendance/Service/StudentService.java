package com.example.Student_Attendance.Service;

import com.example.Student_Attendance.DTO.AttendanceDTO;
import com.example.Student_Attendance.DTO.StudentAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentDTO;
import com.example.Student_Attendance.Entity.Student;

import java.util.List;

public interface StudentService {
    Student registerStudent(StudentDTO studentDTO);
   // void captureAttendance(Long studentId, AttendanceDTO attendanceDTO);
    List<Student> getAllStudents();
    void captureAttendance(List<StudentAttendanceDTO> studentAttendanceList);
}
