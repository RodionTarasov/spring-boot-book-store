package mate.academy.springbootbookstore.service.user;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.model.User;
import mate.academy.springbootbookstore.repository.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Can't find user by email!" + email)
        );
    }
}
