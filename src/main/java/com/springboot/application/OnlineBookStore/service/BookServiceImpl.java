package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.conversions.EntityDTOConversions;
import com.springboot.application.OnlineBookStore.dao.BookRepository;
import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Autowired
    EntityDTOConversions entityDTOConversions;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository)
    {
        bookRepository = theBookRepository;
    }

    @Override
    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
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
}
