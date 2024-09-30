package library.malik.com.Library.management.project.business.abstracts;

import library.malik.com.Library.management.project.entity.Student;

import java.util.List;

public interface StudentService {
    Student registerStudent(Student student);
    Student findByEmail(String email);
    List<Student> getAllStudents();
    void deleteStudent(Long id);
    Student findById(Long id);
}
