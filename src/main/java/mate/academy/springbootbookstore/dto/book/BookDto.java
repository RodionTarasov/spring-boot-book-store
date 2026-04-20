package mate.academy.springbootbookstore.dto.book;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class    BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
    private Set<Long> categoryIds;
}
