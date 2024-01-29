package com.demoSql.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demoSql.Entity.User;
import com.demoSql.Service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String helloUser(Model model) {
        User adminUser = userService.findByUsername("admin");

        if (adminUser != null) {
            model.addAttribute("name", adminUser.getUsername());
            return "home";
        } else {
            return "hello";        
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());

        if (existingUser != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userService.saveUser(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}

