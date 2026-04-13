package mate.academy.springbootbookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @NotEmpty
        @Email
        @Size(min = 8, max = 20)
        String email,
        @NotEmpty
        @Size(min = 8, max = 20)
        String password
) {
}
