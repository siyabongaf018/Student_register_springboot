package com.example.Student_Attendance.Repository;

import com.example.Student_Attendance.Entity.DatesAttended;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatesAttendedRepository extends JpaRepository<DatesAttended, Long> {
}
