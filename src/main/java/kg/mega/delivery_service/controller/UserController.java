package kg.mega.delivery_service.controller;

import kg.mega.delivery_service.model.entity.User;
import kg.mega.delivery_service.model.requests.UserLoginRequest;
import kg.mega.delivery_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registry")
    public void createUser(@Validated @RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/id")
    public User getUserById(@RequestParam("id") Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user, @Validated Long id) {
        userService.updateUser(user, id);
    }

    @DeleteMapping("/delete")
    public void deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/get_all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(userService.loginUser(userLoginRequest));
    }

}
