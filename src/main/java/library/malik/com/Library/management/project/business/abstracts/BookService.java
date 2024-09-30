package library.malik.com.Library.management.project.business.abstracts;

import library.malik.com.Library.management.project.entity.Book;

import java.util.List;

public interface BookService {
    Book registerBook(Book book);
    List<Book> searchBooksByTitle(String title);
    List<Book> getAvailableBooks();
    void updateBook(Book book);
    void deleteBook(Long id);
    Book findById(Long id);
}
