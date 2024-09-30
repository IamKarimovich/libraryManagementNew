package library.malik.com.Library.management.project.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @ManyToMany(mappedBy = "borrowedBooks")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "librarian_id", nullable = false)
    private Librarian librarian;
}
