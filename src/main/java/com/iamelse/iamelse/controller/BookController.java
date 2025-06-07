package com.iamelse.iamelse.controller;

import com.iamelse.iamelse.dto.ApiResponse;
import com.iamelse.iamelse.model.Book;
import com.iamelse.iamelse.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // GET semua buku
    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>("success", "Data tidak ditemukan", books));
        }

        return ResponseEntity.ok(new ApiResponse<>("success", "Berhasil mengambil data", books));
    }

    // GET buku berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        return bookOpt.map(book -> ResponseEntity.ok(new ApiResponse<>("success", "Berhasil mengambil data", book))).orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", "Buku tidak ditemukan", null)));
    }

    // POST tambah buku baru
    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(new ApiResponse<>("success", "Buku berhasil ditambahkan", savedBook));
    }

    // PUT update buku
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        if (bookOpt.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse<>("error", "Buku tidak ditemukan", null));
        }

        Book book = bookOpt.get();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setStock(bookDetails.getStock());

        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(new ApiResponse<>("success", "Buku berhasil diperbarui", updatedBook));
    }

    // DELETE buku
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        if (bookOpt.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse<>("error", "Buku tidak ditemukan", null));
        }

        bookRepository.delete(bookOpt.get());
        return ResponseEntity.ok(new ApiResponse<>("success", "Buku berhasil dihapus", null));
    }
}