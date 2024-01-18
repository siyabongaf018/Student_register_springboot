package com.example.Student_Attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CaptureAttendanceDTO {
    private Long id;
    private String name;
    private String surname;
    private String group;
    private String date;
    @JsonProperty
    private boolean present;


}
