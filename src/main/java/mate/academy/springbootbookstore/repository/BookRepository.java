package mate.academy.springbootbookstore.repository;

import java.util.List;
import mate.academy.springbootbookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
