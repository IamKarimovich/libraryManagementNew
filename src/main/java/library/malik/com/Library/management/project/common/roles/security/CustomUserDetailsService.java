package library.malik.com.Library.management.project.common.roles.security;

import library.malik.com.Library.management.project.dataAccess.abstracts.LibrarianRepository;
import library.malik.com.Library.management.project.entity.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LibrarianRepository librarianRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Librarian librarian = librarianRepository.findByEmail(username);

        if (librarian == null) { // Handle the null case
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                librarian.getEmail(),
                librarian.getPassword(),
                new ArrayList<>() // You can add roles if needed
        );
    }
}