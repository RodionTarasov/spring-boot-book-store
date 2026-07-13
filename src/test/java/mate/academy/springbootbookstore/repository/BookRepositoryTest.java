package mate.academy.springbootbookstore.repository;

import mate.academy.springbootbookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.model.Category;
import mate.academy.springbootbookstore.repository.book.BookRepository;
import mate.academy.springbootbookstore.repository.category.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void findAllByCategories_Id_ReturnsBooksByCategory() {
        Book book = new Book();
        book.setTitle("A Game of Thrones");
        book.setAuthor("George Martin");
        book.setIsbn("231-456-789");
        book.setPrice(BigDecimal.valueOf(21.99));
        Category category = new Category();
        category.setName("High fantasy");
        category.setDescription("Description");
        book.getCategories().add(categoryRepository.save(category));
        bookRepository.save(book);
        Pageable pageable = PageRequest.of(0, 10);

        Page<BookDtoWithoutCategoryIds> actual =
                bookRepository.findAllByCategories_Id(1L, pageable);

        Assertions.assertEquals(1, actual.getTotalElements());
    }
}
