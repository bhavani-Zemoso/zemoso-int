package com.springboot.application.OnlineBookStore.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_name")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String bookName;

    @Column(name = "author")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String author;

    @Column(name = "description")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String description;

    @Column(name = "isbn")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String isbn;

    @Column(name = "price")
    @NotNull(message = "is required")
    @Min(value = 0L, message = "The value must be positive")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cat_id")
    private Category category;


    public Book() {}

    public Book(String bookName, String author, String description, String isbn, double price) {
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                '}';
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
