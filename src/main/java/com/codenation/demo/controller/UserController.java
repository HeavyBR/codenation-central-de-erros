package com.codenation.demo.controller;

import com.codenation.demo.model.User;
import com.codenation.demo.model.request.UserRequest;
import com.codenation.demo.model.response.UserResponse;
import com.codenation.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    private ResponseEntity<Void> save(@RequestBody @Valid UserRequest user, UriComponentsBuilder uriComponentsBuilder) {
        Long createdUser = userService.save(user);
        URI uri = uriComponentsBuilder.path("/api/v1/user/{id}").buildAndExpand(createdUser).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@RequestBody @Valid UserRequest user, @PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        userService.update(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    private ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserResponse> findById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        UserResponse user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}
