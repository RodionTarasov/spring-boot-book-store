package mate.academy.springbootbookstore.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootbookstore.dto.user.UserResponseDto;
import mate.academy.springbootbookstore.exception.RegistrationException;
import mate.academy.springbootbookstore.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserResponseDto register(@RequestBody UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
