package library.malik.com.Library.management.project.business.concretes;

import library.malik.com.Library.management.project.business.abstracts.BorrowingService;
import library.malik.com.Library.management.project.dataAccess.abstracts.BookRepository;
import library.malik.com.Library.management.project.dataAccess.abstracts.BorrowingRepository;
import library.malik.com.Library.management.project.dataAccess.abstracts.StudentRepository;
import library.malik.com.Library.management.project.entity.Book;
import library.malik.com.Library.management.project.entity.Borrowing;
import library.malik.com.Library.management.project.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowedManager implements BorrowingService {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Borrowing borrowBook(Long studentId, Long bookId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available for borrowing");
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setStudent(student);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setReturned(false);

        // Mark book as unavailable
        book.setAvailable(false);
        bookRepository.save(book);

        return borrowingRepository.save(borrowing);
    }

    @Override
    public List<Borrowing> findBorrowingsByStudentId(Long studentId) {
        return borrowingRepository.findByStudentId(studentId);
    }

    @Override
    public List<Borrowing> findBorrowingsByBookId(Long bookId) {
        return borrowingRepository.findByBookId(bookId);
    }

    @Override
    public void returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow();
        borrowing.setReturnDate(LocalDate.now());
        borrowing.setReturned(true);

        // Mark book as available
        Book book = borrowing.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        borrowingRepository.save(borrowing);
    }
}
