package pl.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.example.app.model.CustomUser;
import pl.example.app.model.UserInfo;
import pl.example.app.service.UserService;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<UserInfo> viewUserInfo(@PathVariable int id) {
        UserInfo user = userService.getUserInfo(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> addNewUser(@RequestBody CustomUser user) {
        CustomUser newUser = userService.addNewUser(user);
        return ResponseEntity.ok(newUser.getId());
    }

    @PostMapping(value = "/user/log", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@RequestBody CustomUser user) {
        CustomUser loggedIn = userService.findUser(user);
        System.out.printf("%s %s\n", user.getUsername(), user.getPassword());
        if (!passwordEncoder.matches(user.getPassword(), loggedIn.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(loggedIn.getUsername());


    }
}
