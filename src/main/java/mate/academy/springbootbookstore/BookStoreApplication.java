package mate.academy.springbootbookstore;

import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoreApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("Book Title");
            book.setAuthor("Book Author");
            book.setIsbn("Book Isbn");
            book.setDescription("Description");
            book.setCoverImage("cover.jpg");

            bookService.save(book);
            bookService.findAll().forEach(System.out::println);
        };
    }

}
