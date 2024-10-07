package library.malik.com.Library.management.project.api.controller;

import library.malik.com.Library.management.project.business.abstracts.BookService;
import library.malik.com.Library.management.project.business.abstracts.LibrarianService;
import library.malik.com.Library.management.project.entity.Book;
import library.malik.com.Library.management.project.entity.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private LibrarianService librarianService;

    private final String UPLOAD_DIR = "C:/uploads/images"; // Use a path outside of your project structure



    @PostMapping("/register")
    public ResponseEntity<Book> registerBook(@RequestParam("title") String title,
                                             @RequestParam("author") String author,
                                             @RequestParam("description") String description,
                                             @RequestParam("isbn") String isbn,
                                             @RequestParam("file") MultipartFile file,
                                             @RequestParam("librarianId") Long librarianId) {
        try {
            // Save the file and get the image URL
            String imageUrl = saveImage(file);

            // Create and save the book
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setDescription(description);
            book.setIsbn(isbn);
            book.setImageUrl(imageUrl);
            book.setAvailable(true); // Or set based on your logic

            // Find the librarian by ID and set it to the book
            Librarian librarian = librarianService.findById(librarianId); // Ensure this service is injected
            book.setLibrarian(librarian); // Set the librarian for the book

            Book newBook = bookService.registerBook(book);
            return ResponseEntity.ok(newBook);
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




    private String saveImage(MultipartFile file) throws IOException {
        // Ensure the upload directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directories if they do not exist
        }

        // Define the target file
        String filename = file.getOriginalFilename();
        File targetFile = new File(uploadDir, filename);

        // Save the file
        file.transferTo(targetFile);

        // Return the relative URL for the image
        return "/images/" + filename; // Adjust as needed for your frontend
    }



    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok(book);
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
