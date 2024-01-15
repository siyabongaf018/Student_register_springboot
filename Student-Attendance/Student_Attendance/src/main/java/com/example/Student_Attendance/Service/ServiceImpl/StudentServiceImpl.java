package com.example.Student_Attendance.Service.ServiceImpl;

import com.example.Student_Attendance.DTO.AttendanceDTO;
import com.example.Student_Attendance.DTO.StudentAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentDTO;
import com.example.Student_Attendance.Entity.DatesAttended;
import com.example.Student_Attendance.Entity.Student;
import com.example.Student_Attendance.Exception.ResourceNotFoundException;
import com.example.Student_Attendance.Repository.DatesAttendedRepository;
import com.example.Student_Attendance.Repository.StudentRepository;
import com.example.Student_Attendance.Service.StudentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final DatesAttendedRepository datesAttendedRepository;



    @Override
    public Student registerStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setGroupName(studentDTO.getGroup());

        return studentRepository.save(student);
    }
//
//    @Override
//    @Transactional
//    public void captureAttendance(Long studentId, AttendanceDTO attendanceDTO) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
//
//        String date = attendanceDTO.getDate();
//
//        // Check if attendance for the given date already exists for the student
//        Optional<DatesAttended> existingAttendanceOptional = student.getDatesAttendedSet().stream()
//                .filter(att -> att.getDate().equals(date))
//                .findFirst();
//
//        //update the existing record
//        if (existingAttendanceOptional.isPresent()) {
//            DatesAttended existingAttendance = existingAttendanceOptional.get();
//            existingAttendance.setPresent(attendanceDTO.isPresent());
//            datesAttendedRepository.save(existingAttendance);
//        }
//        // create a new record
//        else {
//            DatesAttended attendance = new DatesAttended();
//            attendance.setDate(date);
//            attendance.setPresent(attendanceDTO.isPresent());
//
//            student.getDatesAttendedSet().add(attendance);
//            attendance.getStudents().add(student);
//
//            datesAttendedRepository.save(attendance);
//        }
//    }


    @Override
    @Transactional
    public void captureAttendance(List<StudentAttendanceDTO> studentAttendanceList) {
        for (StudentAttendanceDTO studentAttendanceDTO : studentAttendanceList) {
            Long studentId = studentAttendanceDTO.getId();
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

            List<AttendanceDTO> attendanceDTOList = studentAttendanceDTO.getDate();

            for (AttendanceDTO attendanceDTO : attendanceDTOList) {
                String date = attendanceDTO.getDate();

                // Check if attendance for the given date already exists for the student
                Optional<DatesAttended> existingAttendanceOptional = student.getDatesAttendedSet().stream()
                        .filter(att -> att.getDate().equals(date))
                        .findFirst();

                if (existingAttendanceOptional.isPresent()) {
                    //update the existing record
                    DatesAttended existingAttendance = existingAttendanceOptional.get();
                    existingAttendance.setPresent(attendanceDTO.isPresent());
                    datesAttendedRepository.save(existingAttendance);
                } else {
                    //create a new record
                    DatesAttended attendance = new DatesAttended();
                    attendance.setDate(date);
                    attendance.setPresent(attendanceDTO.isPresent());

                    student.getDatesAttendedSet().add(attendance);
                    attendance.getStudents().add(student);

                    datesAttendedRepository.save(attendance);
                }
            }
        }
    }
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
