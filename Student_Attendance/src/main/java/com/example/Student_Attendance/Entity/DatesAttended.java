package com.example.Student_Attendance.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dates_attended")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatesAttended {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private boolean isPresent;

    @ManyToMany(mappedBy = "datesAttendedList")
    private List<Student> students = new ArrayList<>();
}
