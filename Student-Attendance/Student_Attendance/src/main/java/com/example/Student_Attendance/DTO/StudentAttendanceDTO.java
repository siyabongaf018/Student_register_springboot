package com.example.Student_Attendance.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendanceDTO {
    private Long id;
    private String name;
    private String surname;
    private String group;
    private List<AttendanceDTO> date;
}
