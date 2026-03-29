package mate.academy.springbootbookstore.service.user;

import mate.academy.springbootbookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootbookstore.dto.user.UserResponseDto;
import mate.academy.springbootbookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
