package mate.academy.springbootbookstore.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull
    private String title;
    private String author;
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
