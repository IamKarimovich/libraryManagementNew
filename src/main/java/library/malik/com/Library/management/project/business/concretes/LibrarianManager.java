package library.malik.com.Library.management.project.business.concretes;

import library.malik.com.Library.management.project.business.abstracts.LibrarianService;
import library.malik.com.Library.management.project.dataAccess.abstracts.LibrarianRepository;
import library.malik.com.Library.management.project.entity.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibrarianManager implements LibrarianService {
    @Autowired
    private LibrarianRepository librarianRepository;

    @Override
    public Librarian signUp(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    @Override
    public Librarian login(String email, String password) {
        Librarian librarian = librarianRepository.findByEmail(email);
        if (librarian != null && librarian.getPassword().equals(password)) {
            return librarian;
        }
        return null; // or throw an exception
    }

    @Override
    public Librarian findById(Long id) {
        Optional<Librarian> librarian = librarianRepository.findById(id);
        return librarian.orElse(null);
    }

    @Override
    public void updateLibrarian(Librarian librarian) {
        librarianRepository.save(librarian);
    }

    @Override
    public void deleteLibrarian(Long id) {
        librarianRepository.deleteById(id);
    }
}
