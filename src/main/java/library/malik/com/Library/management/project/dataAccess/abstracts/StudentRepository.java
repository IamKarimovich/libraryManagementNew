package library.malik.com.Library.management.project.dataAccess.abstracts;

import library.malik.com.Library.management.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Additional query methods, if necessary
    Student findByEmail(String email); // For student login
}
