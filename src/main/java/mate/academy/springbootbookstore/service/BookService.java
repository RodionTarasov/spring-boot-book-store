package mate.academy.springbootbookstore.service;

import java.util.List;
import mate.academy.springbootbookstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
