package com.iamelse.iamelse.repository;

import com.iamelse.iamelse.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
