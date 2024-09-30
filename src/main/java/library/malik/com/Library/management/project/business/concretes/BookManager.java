package library.malik.com.Library.management.project.business.concretes;

import library.malik.com.Library.management.project.business.abstracts.BookService;
import library.malik.com.Library.management.project.dataAccess.abstracts.BookRepository;
import library.malik.com.Library.management.project.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
}
