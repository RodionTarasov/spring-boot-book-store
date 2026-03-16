package mate.academy.springbootbookstore.repository.book;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.repository.SpecificationProvider;
import mate.academy.springbootbookstore.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(b -> b.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException
                        ("Can't find correct specification provider for kay" + key));
    }
}
