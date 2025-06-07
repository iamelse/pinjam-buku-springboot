package com.iamelse.iamelse.controller;

import com.iamelse.iamelse.dto.ApiResponse;
import com.iamelse.iamelse.dto.BorrowRequest;
import com.iamelse.iamelse.dto.BorrowResponse;
import com.iamelse.iamelse.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BorrowResponse>>> getAllBorrows() {
        List<BorrowResponse> borrows = borrowService.getAllBorrows();
        return ResponseEntity.ok(new ApiResponse<>("success", "Data peminjaman berhasil diambil", borrows));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> borrowBook(@Valid @RequestBody BorrowRequest borrowRequest) {
        String result = borrowService.borrowBook(borrowRequest.getBorrowerName(), borrowRequest.getBookId());

        if ("Peminjaman berhasil".equals(result)) {
            return ResponseEntity.ok(new ApiResponse<>("success", result, null));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse<>("error", result, null));
        }
    }
}