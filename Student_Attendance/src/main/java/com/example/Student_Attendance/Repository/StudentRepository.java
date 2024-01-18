package com.example.Student_Attendance.Repository;

import com.example.Student_Attendance.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
