package mate.academy.springbootbookstore.repository.book.spec;

import java.util.Arrays;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return "title";
    }

    @Override
    public Specification<Book> getSpecification(String[] param) {
        return (root, query, criteriaBuilder) -> root.<String>get("title")
                .in(Arrays.stream(param).toArray());
    }
}
