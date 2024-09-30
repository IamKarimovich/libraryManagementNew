package library.malik.com.Library.management.project.api.controller;

import library.malik.com.Library.management.project.business.abstracts.LibrarianService;
import library.malik.com.Library.management.project.entity.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PostMapping("/signup")
    public ResponseEntity<Librarian> signUp(@RequestBody Librarian librarian) {
        Librarian newLibrarian = librarianService.signUp(librarian);
        return ResponseEntity.ok(newLibrarian);
    }

    @PostMapping("/login")
    public ResponseEntity<Librarian> login(@RequestParam String email, @RequestParam String password) {
        Librarian librarian = librarianService.login(email, password);
        if (librarian != null) {
            return ResponseEntity.ok(librarian);
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Librarian> getLibrarianById(@PathVariable Long id) {
        Librarian librarian = librarianService.findById(id);
        if (librarian != null) {
            return ResponseEntity.ok(librarian);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Librarian> updateLibrarian(@PathVariable Long id, @RequestBody Librarian librarian) {
        librarian.setId(id);
        librarianService.updateLibrarian(librarian);
        return ResponseEntity.ok(librarian);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrarian(@PathVariable Long id) {
        librarianService.deleteLibrarian(id);
        return ResponseEntity.noContent().build();
    }
}