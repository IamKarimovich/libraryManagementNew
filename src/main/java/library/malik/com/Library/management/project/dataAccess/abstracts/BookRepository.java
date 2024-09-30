package library.malik.com.Library.management.project.dataAccess.abstracts;

import library.malik.com.Library.management.project.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Find books by title or availability status
    List<Book> findByTitleContainingIgnoreCase(String title); // Search books by title
    List<Book> findByIsAvailable(boolean isAvailable); // Find available or unavailable books
}

