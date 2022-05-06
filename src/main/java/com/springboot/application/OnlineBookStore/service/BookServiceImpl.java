package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.conversions.EntityDTOConversions;
import com.springboot.application.OnlineBookStore.dao.repository.BookRepository;
import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.service_interface.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    EntityDTOConversions entityDTOConversions;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository)
    {
        bookRepository = theBookRepository;
    }

    private int BOOKS_PER_PAGE = 4;
    @Override
    public List<BookDTO> findAll(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum-1, BOOKS_PER_PAGE);
        Page<Book> page = bookRepository.findAll(pageable);
        List<Book> books = page.getContent();

        System.out.println("PageNum = " + pageNum);
        System.out.println("Total elements = " +page.getTotalElements());
        System.out.println("Total pages = " + page.getTotalPages());

        return books.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);

        Book theBook = null;

        if(result.isPresent())
            theBook = result.get();
        else
            throw new RuntimeException("Did not find book id - " + theId);

        return entityDTOConversions.convertFromEntityToDTO(theBook);
    }

    @Override
    public void save(BookDTO theBook) {

        bookRepository.save(entityDTOConversions.convertFromDTOToEntity(theBook));

    }

    @Override
    public void deleteById(int theId) {

        bookRepository.deleteById(theId);
    }

    public List<BookDTO> search(String keyword)
    {
        List<Book> books = bookRepository.search(keyword);
        return books.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }
}
