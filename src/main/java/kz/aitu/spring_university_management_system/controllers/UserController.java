package kz.aitu.spring_university_management_system.controllers;

import kz.aitu.spring_university_management_system.models.User;
import kz.aitu.spring_university_management_system.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user_table")
public class UserController {
    private final UserServiceInterface service;

    public UserController(UserServiceInterface service) {
        this.service = service;
    }

    @GetMapping("hello")
    public String sayHello() {
        return "Алматынын тундери ай";
    }

    @GetMapping("/")
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") int id) {
        User user = service.getById(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404 error
        return new ResponseEntity<>(user, HttpStatus.OK); //200 error
    }

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = service.create(user);
        if (createdUser == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); //201
    }

    @GetMapping("/surname/{user_surnme}")
    public List<User> getAllBySurname(@PathVariable("user_surname") String surname) {
        return service.getBySurname(surname);
    }
}

