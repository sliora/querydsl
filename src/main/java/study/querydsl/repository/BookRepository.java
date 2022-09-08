package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
