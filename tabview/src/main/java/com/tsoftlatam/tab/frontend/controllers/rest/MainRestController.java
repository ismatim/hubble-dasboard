package com.tsoftlatam.tab.frontend.controllers.rest;

import com.tsoftlatam.tab.frontend.models.Book;
import com.tsoftlatam.tab.frontend.repositories.LmsTblRepository;
import com.tsoftlatam.tab.frontend.services.LmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MainRestController {

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
}
