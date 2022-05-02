package com.springboot.application.OnlineBookStore.dto;

import com.springboot.application.OnlineBookStore.entity.Category;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BookDTO {

    private int bookId;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String bookName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String author;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String description;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String isbn;

    @NotNull(message = "is required")
    @Min(value = 0L, message = "The value must be positive")
    private String price;

    private Category category;
}
