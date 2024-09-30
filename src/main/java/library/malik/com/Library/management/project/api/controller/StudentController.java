package library.malik.com.Library.management.project.api.controller;

import library.malik.com.Library.management.project.business.abstracts.StudentService;
import library.malik.com.Library.management.project.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student newStudent = studentService.registerStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    @GetMapping("/email")
    public ResponseEntity<Student> getStudentByEmail(@RequestParam String email) {
        Student student = studentService.findByEmail(email);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
