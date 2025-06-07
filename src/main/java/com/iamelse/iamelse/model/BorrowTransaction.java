package com.iamelse.iamelse.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_transactions")
public class BorrowTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String borrowerName; // nama peminjam

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(nullable = false)
    private LocalDateTime borrowDate;

    @Column
    private LocalDateTime returnDate;  // nullable, bisa null jika belum dikembalikan

    @Column(length = 20)
    private String status;  // contoh: "dipinjam", "kembali"

    // constructors
    public BorrowTransaction() {}

    public BorrowTransaction(String borrowerName, Book book, LocalDateTime borrowDate) {
        this.borrowerName = borrowerName;
        this.book = book;
        this.borrowDate = borrowDate;
        this.status = "dipinjam"; // default status saat peminjaman dibuat
    }

    // getters and setters
    public Long getId() { return id; }

    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public LocalDateTime getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDateTime borrowDate) { this.borrowDate = borrowDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}