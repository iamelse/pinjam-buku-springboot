package com.iamelse.iamelse.repository;

import com.iamelse.iamelse.model.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<BorrowTransaction, Long> {
}
