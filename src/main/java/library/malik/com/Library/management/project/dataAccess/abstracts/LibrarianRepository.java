package library.malik.com.Library.management.project.dataAccess.abstracts;


import library.malik.com.Library.management.project.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    // Additional query methods, if necessary
    Librarian findByEmail(String email); // For login by email
}
