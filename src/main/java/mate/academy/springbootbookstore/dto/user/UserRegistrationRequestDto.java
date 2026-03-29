package mate.academy.springbootbookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import mate.academy.springbootbookstore.validation.FieldMatch;
import org.hibernate.validator.constraints.Length;

@Data
@FieldMatch(
        first = "password",
        second = "repeatPassword",
        message = "Passwords must match"
)
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 6, max = 35)
    private String password;

    @NotBlank
    @Length(min = 6, max = 35)
    private String repeatPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String shippingAddress;
}
