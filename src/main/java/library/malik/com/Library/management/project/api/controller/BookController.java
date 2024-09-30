package library.malik.com.Library.management.project.api.controller;

import library.malik.com.Library.management.project.business.abstracts.BookService;
import library.malik.com.Library.management.project.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/register")
    public ResponseEntity<Book> registerBook(@RequestBody Book book) {
        Book newBook = bookService.registerBook(book);
        return ResponseEntity.ok(newBook);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> availableBooks = bookService.getAvailableBooks();
        return ResponseEntity.ok(availableBooks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        bookService.updateBook(book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}