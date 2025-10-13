package mate.academy.springbootbookstore.service;

import mate.academy.springbootbookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
}
