package library.malik.com.Library.management.project.api.controller;
import library.malik.com.Library.management.project.business.abstracts.BorrowingService;
import library.malik.com.Library.management.project.entity.Borrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowedController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ResponseEntity<Borrowing> borrowBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        Borrowing borrowing = borrowingService.borrowBook(studentId, bookId);
        return ResponseEntity.ok(borrowing);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Borrowing>> getBorrowingsByStudentId(@PathVariable Long studentId) {
        List<Borrowing> borrowings = borrowingService.findBorrowingsByStudentId(studentId);
        return ResponseEntity.ok(borrowings);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Borrowing>> getBorrowingsByBookId(@PathVariable Long bookId) {
        List<Borrowing> borrowings = borrowingService.findBorrowingsByBookId(bookId);
        return ResponseEntity.ok(borrowings);
    }

    @PutMapping("/return/{borrowingId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long borrowingId) {
        borrowingService.returnBook(borrowingId);
        return ResponseEntity.noContent().build();
    }
}
