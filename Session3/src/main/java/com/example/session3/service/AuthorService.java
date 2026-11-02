package com.example.session3.service;

import com.example.session3.model.Author;
import com.example.session3.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author createAuthor(Author author) {
        if(author.getName() == null || author.getName().isBlank()){
            throw new IllegalArgumentException("Tên tác giả không được để trống");
        }

        if(author.getName().length() > 100 ){
            throw new IllegalArgumentException("Tên tác giả không được vượt quá 100 kí tự");
        }
        author.setId(null);
        return authorRepository.save(author);
    }

    @Transactional
    public boolean deleteAuthor(Long id) {

        Author author = authorRepository.findById(id).orElse(null);

        if (author == null) {
            return false;
        }

        if("Admin".equalsIgnoreCase(author.getName().trim())) {
            throw new IllegalArgumentException(" Không được phép xóa tài khoản Admin");
        }

        authorRepository.delete(author);
        return true;
    }

    public List<Author> searchAuthors(String keyword){
       return authorRepository.findByNameContainingIgnoreCase(keyword);
    }
}