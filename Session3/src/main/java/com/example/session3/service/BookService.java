package com.example.session3.service;

import com.example.session3.dto.BookRequest;
import com.example.session3.model.Author;
import com.example.session3.model.Book;
import com.example.session3.repository.AuthorRepository;
import com.example.session3.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Book createBook(BookRequest request) {
        if (request.getAuthorId() == null) {
            throw new IllegalArgumentException("ID tác giả không được để trống");
        }
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Tên sách không được để trống");
        }
        if (request.getPrice() < 0) {
            throw new IllegalArgumentException("Giá sách không được âm");
        }
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(
                () -> new IllegalArgumentException("Tác giả không tồn tại"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }
}
