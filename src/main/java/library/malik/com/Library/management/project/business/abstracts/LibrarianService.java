package library.malik.com.Library.management.project.business.abstracts;

import library.malik.com.Library.management.project.entity.Librarian;

public interface LibrarianService {
    Librarian signUp(Librarian librarian);
    Librarian login(String email, String password);
    Librarian findById(Long id);
    void updateLibrarian(Librarian librarian);
    void deleteLibrarian(Long id);

}
