package com.example.Student_Attendance.DTO;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
    private String date;
    private boolean isPresent;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
