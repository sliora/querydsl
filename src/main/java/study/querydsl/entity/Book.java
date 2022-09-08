package study.querydsl.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Book {
    @Id @GeneratedValue
    private Integer id;
    private String isbn;
    private String title;

    @ManyToOne
    @JoinColumn(name = "bookstore_id")
    private BookStore bookStore;

}
