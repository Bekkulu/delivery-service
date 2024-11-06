package kg.mega.delivery_service.service;

import kg.mega.delivery_service.exceptions.UserNotFoundException;
import kg.mega.delivery_service.model.entity.User;
import kg.mega.delivery_service.model.requests.UserLoginRequest;
import kg.mega.delivery_service.model.responses.UserLoginResponse;
import kg.mega.delivery_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        User user = new User();

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return user;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, Long id) {
        User user1 = userRepository.findById(id).orElse(null);
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        userRepository.save(user1);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        return null;
    }
}
