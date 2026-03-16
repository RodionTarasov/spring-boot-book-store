package mate.academy.springbootbookstore.repository.book.spec;

import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return "author";
    }

    @Override
    public Specification<Book> getSpecification(String[] param) {
        return (root, query, criteriaBuilder) -> root.get("author")
                .in(Arrays.asList(param).toArray());
    }
}
