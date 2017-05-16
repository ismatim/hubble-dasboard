package com.tsoftlatam.tab.frontend.repositories;


import com.tsoftlatam.tab.frontend.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LmsTblRepository extends CrudRepository<Book, Long>{

}
