package com.iamelse.iamelse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BorrowRequest {

    @NotBlank(message = "Nama peminjam harus diisi")
    private String borrowerName;

    @NotNull(message = "Book ID is required")
    private Long bookId;

    // getters and setters
    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}