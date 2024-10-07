package library.malik.com.Library.management.project.dataAccess.abstracts;

import library.malik.com.Library.management.project.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    Librarian findByEmail(String email);
}