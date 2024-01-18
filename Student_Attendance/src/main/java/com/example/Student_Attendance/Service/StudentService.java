package com.example.Student_Attendance.Service;

import com.example.Student_Attendance.DTO.CaptureAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentRegisterDTO;
import com.example.Student_Attendance.Entity.Student;

import java.util.List;

public interface StudentService {
    Student registerStudent(StudentRegisterDTO studentRegisterDTO);
    void captureAttendance(List<CaptureAttendanceDTO> studentAttendanceList);
    List<StudentAttendanceDTO> getAllStudents();
}
