package mate.academy.springbootbookstore.repository.book.spec;

import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String AUTHOR = "author";

    @Override
    public String getKey() {
        return AUTHOR;
    }

    @Override
    public Specification<Book> getSpecification(String[] param) {
        return (root, query, criteriaBuilder) -> root.get(AUTHOR)
                .in(Arrays.asList(param).toArray());
    }
}
