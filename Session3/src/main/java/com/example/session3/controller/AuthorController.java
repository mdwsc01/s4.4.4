package com.example.session3.controller;

import com.example.session3.model.Author;
import com.example.session3.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);

        if (author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tác giả với ID là:" + id);
        }
        return ResponseEntity.ok(author);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Author>> searchAuthors(@RequestParam("name") String keyword) {
        List<Author> results = authorService.searchAuthors(keyword);
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            boolean deleted = authorService.deleteAuthor(id);
            if(!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tác giả với id là: "+id);
            }
            return ResponseEntity.ok("Xóa thành công");
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }




}
