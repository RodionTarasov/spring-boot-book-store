package mate.academy.springbootbookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @NotBlank
        @Email
        @Size(min = 6, max = 20)
        String email,
        @NotBlank
        @Size(min = 6, max = 20)
        String password
) {
}
