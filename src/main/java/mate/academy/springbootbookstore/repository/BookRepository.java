package mate.academy.springbootbookstore.repository;

import mate.academy.springbootbookstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
