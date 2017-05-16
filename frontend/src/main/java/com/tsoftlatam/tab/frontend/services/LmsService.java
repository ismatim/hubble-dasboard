package com.tsoftlatam.tab.frontend.services;

import com.tsoftlatam.tab.frontend.models.Book;
import com.tsoftlatam.tab.frontend.repositories.LmsTblRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LmsService {

    @Autowired
    private LmsTblRepository lmsTblRepository;

    public Collection<Book> findAllBooks(){
        List<Book> books = new ArrayList<Book>();
        for (Book book:lmsTblRepository.findAll()) {
            books.add(book);
        }
        return books;
    }

    public void deleteBook(long id){
        lmsTblRepository.delete(id);
    }

    public Book findBookById(long id){
        return lmsTblRepository.findOne(id);
    }
}
