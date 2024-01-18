package com.example.Student_Attendance.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegisterDTO {
    private String name;
    private String surname;
    private String group;
}
