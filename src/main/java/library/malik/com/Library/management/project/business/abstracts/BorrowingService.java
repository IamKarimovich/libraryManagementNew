package library.malik.com.Library.management.project.business.abstracts;

import library.malik.com.Library.management.project.entity.Borrowing;

import java.util.List;

public interface BorrowingService {
    Borrowing borrowBook(Long studentId, Long bookId);
    List<Borrowing> findBorrowingsByStudentId(Long studentId);
    List<Borrowing> findBorrowingsByBookId(Long bookId);
    void returnBook(Long borrowingId);
}
