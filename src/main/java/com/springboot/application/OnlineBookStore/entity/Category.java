package com.springboot.application.OnlineBookStore.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private int categoryId;

    @Column(name = "cat_name")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "category",
            cascade = CascadeType.ALL)
    private List<Book> books;


    public Category() {}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void add(Book tempBook)
    {
        if(books == null)
            books = new ArrayList<>();

        books.add(tempBook);

        tempBook.setCategory(this);
    }
}
