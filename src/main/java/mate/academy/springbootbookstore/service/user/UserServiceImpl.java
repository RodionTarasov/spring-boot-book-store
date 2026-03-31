package mate.academy.springbootbookstore.service.user;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootbookstore.dto.user.UserResponseDto;
import mate.academy.springbootbookstore.exception.RegistrationException;
import mate.academy.springbootbookstore.mapper.UserMapper;
import mate.academy.springbootbookstore.model.User;
import mate.academy.springbootbookstore.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("User with this email already exists: " +
                    requestDto.getEmail());
        }

        User user = userMapper.toModel(requestDto);
        return userMapper.toDto(user);
    }
}
