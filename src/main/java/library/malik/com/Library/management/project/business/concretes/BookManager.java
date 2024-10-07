package library.malik.com.Library.management.project.business.concretes;

import library.malik.com.Library.management.project.business.abstracts.BookService;
import library.malik.com.Library.management.project.core.exceptions.ResourceNotFoundException;
import library.malik.com.Library.management.project.dataAccess.abstracts.BookRepository;
import library.malik.com.Library.management.project.entity.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book registerBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> getAvailableBooks() {
        return bookRepository.findByIsAvailable(true);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + id + " not found"));
    }
}
