package mate.academy.springbootbookstore.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootbookstore.dto.user.UserResponseDto;
import mate.academy.springbootbookstore.exception.RegistrationException;
import mate.academy.springbootbookstore.mapper.UserMapper;
import mate.academy.springbootbookstore.model.Role;
import mate.academy.springbootbookstore.model.User;
import mate.academy.springbootbookstore.repository.role.RoleRepository;
import mate.academy.springbootbookstore.repository.user.UserRepository;
import mate.academy.springbootbookstore.service.shoppingCart.ShoppingCartService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ShoppingCartService shoppingCartService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("User with this email already exists: " +
                    requestDto.getEmail());
        }

        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        Role role = roleRepository.findByRole(Role.RoleName.USER).orElseThrow(
                () -> new RuntimeException(
                        "Default role ROLE_USER not found in database.")
        );

        user.getRoles().add(role);

        User savedUser = userRepository.save(user);

        shoppingCartService.createCart(savedUser);

        return userMapper.toDto(savedUser);
    }
}
