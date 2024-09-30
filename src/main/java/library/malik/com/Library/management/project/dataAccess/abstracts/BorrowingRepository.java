package library.malik.com.Library.management.project.dataAccess.abstracts;

import library.malik.com.Library.management.project.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    // Find borrowings by student or book
    List<Borrowing> findByStudentId(Long studentId); // For tracking student borrowing history
    List<Borrowing> findByBookId(Long bookId); // For checking if a book is borrowed
}

