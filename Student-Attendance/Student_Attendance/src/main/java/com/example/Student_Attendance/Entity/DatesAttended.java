package com.example.Student_Attendance.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dates_attended")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatesAttended {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private boolean isPresent;

    @ManyToMany(mappedBy = "datesAttendedSet")
    private Set<Student> students = new HashSet<>();
}
