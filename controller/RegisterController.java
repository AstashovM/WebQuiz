package engine.controller;

import engine.entity.User;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/api/register", consumes = "application/json")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }
}
