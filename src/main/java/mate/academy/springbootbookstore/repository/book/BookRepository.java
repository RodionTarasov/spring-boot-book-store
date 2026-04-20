package mate.academy.springbootbookstore.repository.book;

import mate.academy.springbootbookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.springbootbookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Page<BookDtoWithoutCategoryIds> findAllByCategories_Id(Long id, Pageable pageable);
}
