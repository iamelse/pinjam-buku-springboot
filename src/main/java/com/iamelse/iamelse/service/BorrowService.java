package com.iamelse.iamelse.service;

import com.iamelse.iamelse.dto.BorrowResponse;
import com.iamelse.iamelse.model.Book;
import com.iamelse.iamelse.model.BorrowTransaction;
import com.iamelse.iamelse.repository.BookRepository;
import com.iamelse.iamelse.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    public String borrowBook(String borrowerName, Long bookId) {
        if (borrowerName == null || borrowerName.isBlank()) {
            return "Nama peminjam harus diisi";
        }

        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isEmpty()) {
            return "Buku tidak ditemukan";
        }

        Book book = bookOpt.get();
        if (book.getStock() <= 0) {
            return "Stok buku habis";
        }

        // Kurangi stok buku
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        // Buat transaksi peminjaman baru dengan status "dipinjam"
        BorrowTransaction transaction = new BorrowTransaction(borrowerName, book, LocalDateTime.now());
        borrowRepository.save(transaction);

        return "Peminjaman berhasil";
    }

    public List<BorrowResponse> getAllBorrows() {
        List<BorrowTransaction> transactions = borrowRepository.findAll();

        return transactions.stream()
                .map(this::mapToBorrowResponse)
                .collect(Collectors.toList());
    }

    private BorrowResponse mapToBorrowResponse(BorrowTransaction transaction) {
        BorrowResponse response = new BorrowResponse();

        response.setBorrowId(transaction.getId());
        response.setBorrowerName(transaction.getBorrowerName());  // gunakan borrowerName bukan userId/userName

        response.setBookId(transaction.getBook().getId());
        response.setBookTitle(transaction.getBook().getTitle());
        response.setBorrowDate(transaction.getBorrowDate());
        response.setReturnDate(transaction.getReturnDate()); // bisa null
        response.setStatus(transaction.getStatus()); // "dipinjam", "kembali", dll

        return response;
    }
}