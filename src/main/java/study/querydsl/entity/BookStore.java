package study.querydsl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class BookStore {
    @Id @GeneratedValue
    @Column(name = "bookstore_id")
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "bookStore")
    private List<Book> book = new ArrayList<>();

    void changeBookStore(Book book) {
        book.setBookStore(this);
        getBook().add(book);

    }
}
