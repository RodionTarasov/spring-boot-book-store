package mate.academy.springbootbookstore.dto.book;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @Pattern(regexp = ".*\\S.*", message = "author must not be blank")
    private String author;
    @NotBlank
    private String isbn;
    @NotNull
    @Positive
    private BigDecimal price;
    private String description;
    private String coverImage;
    @NotEmpty
    private Set<Long> categories;
}
