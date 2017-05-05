package com.tsoftlatam.tab.frontend.controllers.rest;

import com.tsoftlatam.tab.frontend.models.Book;
import com.tsoftlatam.tab.frontend.models.restClients.BookClient;
import com.tsoftlatam.tab.frontend.repositories.LmsTblRepository;
import com.tsoftlatam.tab.frontend.services.LmsService;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class MainRestController {

    private BookClient bookClient;
    private String bookClientUrl = "http://localhost:8080/findAllBooks";

    //La anotación @Autowired permite la inyección de dependencias, no es necesario implementar la interfaz
    @Autowired
    private LmsService lmsService;

    //Simple prueba de retorno de un string
    @GetMapping(value = "/")
    public String hello(){
        return "Hello World!";
    }

    //Llamada al método findAllBooks del servicio
    @GetMapping(value = "/findAllBooks")
    public Collection<Book> getAllBooks(){

        return lmsService.findAllBooks();

    }

    //Eliminación de un registro a partir de su ID
    @GetMapping(value = "/deleteBook")
    public void deleteBook(@RequestParam long id){

        lmsService.deleteBook(id);

    }

    //Cliente REST con feign que devuelve un string
    @GetMapping("/bookData")
    public List<Book> bookData(){
        bookClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(BookClient.class, bookClientUrl);
        return bookClient.findAll();
    }

    //Cliente REST con feign que devuelve una lista

}
