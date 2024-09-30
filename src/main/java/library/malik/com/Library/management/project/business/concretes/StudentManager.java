package library.malik.com.Library.management.project.business.concretes;

import library.malik.com.Library.management.project.business.abstracts.StudentService;
import library.malik.com.Library.management.project.dataAccess.abstracts.StudentRepository;
import library.malik.com.Library.management.project.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentManager implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }
}
