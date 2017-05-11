package com.tsoftlatam.tab.frontend.models.restClients;

import com.tsoftlatam.tab.frontend.models.Book;
import feign.RequestLine;

import java.util.List;

public interface BookClient {

    @RequestLine("GET /")
    List<Book> findAll();
}
