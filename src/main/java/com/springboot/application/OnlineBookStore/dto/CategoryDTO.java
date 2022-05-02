package com.springboot.application.OnlineBookStore.dto;

import com.springboot.application.OnlineBookStore.entity.Book;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CategoryDTO {

    private int categoryId;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String categoryName;

    private List<Book> books;

}
