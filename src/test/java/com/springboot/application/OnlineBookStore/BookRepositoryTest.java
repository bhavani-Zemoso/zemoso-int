package com.springboot.application.OnlineBookStore;

import com.springboot.application.OnlineBookStore.dao.repository.BookRepository;
import com.springboot.application.OnlineBookStore.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testListFirstPage()
    {
        int pageNumber = 0;
        int pageSize = 4;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Book> page = bookRepository.findAll(pageable);

        List<Book> books = page.getContent();


        books.forEach(book -> System.out.println(book));

        assertThat(books.size()).isEqualTo(pageSize);
    }
}