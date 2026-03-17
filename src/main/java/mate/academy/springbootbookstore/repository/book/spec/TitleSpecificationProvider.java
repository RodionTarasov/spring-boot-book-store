package mate.academy.springbootbookstore.repository.book.spec;

import java.util.Arrays;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    private static final String TITLE = "title";

    @Override
    public String getKey() {
        return TITLE;
    }

    @Override
    public Specification<Book> getSpecification(String[] param) {
        return (root, query, criteriaBuilder) -> root.<String>get(TITLE)
                .in(Arrays.stream(param).toArray());
    }
}
