package study.querydsl.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class BookTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void save() {
        BookStore bookStore = new BookStore();
        bookStore.setName("helloStore");
        em.persist(bookStore);

        Book book = new Book();
        book.setTitle("귀칼");
        book.setIsbn("gggg");

        bookStore.changeBookStore(book);

        bookRepository.save(book);

    }

}