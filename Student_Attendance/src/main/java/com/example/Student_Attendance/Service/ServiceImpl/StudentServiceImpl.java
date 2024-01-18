package com.example.Student_Attendance.Service.ServiceImpl;

import com.example.Student_Attendance.DTO.AttendanceDTO;
import com.example.Student_Attendance.DTO.CaptureAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentAttendanceDTO;
import com.example.Student_Attendance.DTO.StudentRegisterDTO;
import com.example.Student_Attendance.Entity.DatesAttended;
import com.example.Student_Attendance.Entity.Student;
import com.example.Student_Attendance.Repository.DatesAttendedRepository;
import com.example.Student_Attendance.Repository.StudentRepository;
import com.example.Student_Attendance.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final DatesAttendedRepository datesAttendedRepository;


    @Override
    public Student registerStudent(StudentRegisterDTO studentRegisterDTO) {
        Student student = new Student();
        student.setName(studentRegisterDTO.getName());
        student.setSurname(studentRegisterDTO.getSurname());
        student.setGroupName(studentRegisterDTO.getGroup());

        return studentRepository.save(student);
    }


    @Override
    public void captureAttendance(List<CaptureAttendanceDTO> studentAttendanceList) {
        for (CaptureAttendanceDTO studentAttendanceDTO : studentAttendanceList) {
            Long studentId = studentAttendanceDTO.getId();
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
            String date = studentAttendanceDTO.getDate();

            //Check if attendance for the given date already exists for the student
            Optional<DatesAttended> existingAttendanceOptional = student.getDatesAttendedList().stream()
                    .filter(att -> att.getDate().equals(date))
                    .findFirst();

            if (existingAttendanceOptional.isPresent()) {
                // update the existing record
                DatesAttended existingAttendance = existingAttendanceOptional.get();
                existingAttendance.setPresent(studentAttendanceDTO.isPresent());
                datesAttendedRepository.save(existingAttendance);
            } else {
                //create a new record
                DatesAttended attendance = new DatesAttended();
                attendance.setDate(date);
                attendance.setPresent(studentAttendanceDTO.isPresent());

                student.getDatesAttendedList().add(attendance);
                attendance.getStudents().add(student);

                datesAttendedRepository.save(attendance);
            }

        }
    }
    @Override
    public List<StudentAttendanceDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //convert to DTO for Json Deserialization
    private StudentAttendanceDTO convertToDTO(Student student) {
        StudentAttendanceDTO studentDTO = new StudentAttendanceDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setGroup(student.getGroupName());

        List<AttendanceDTO> attendanceDTOList = student.getDatesAttendedList().stream()
                .map(att -> new AttendanceDTO(att.getDate(), att.isPresent()))
                .collect(Collectors.toList());

        studentDTO.setDate(attendanceDTOList);
        return studentDTO;
    }
}
