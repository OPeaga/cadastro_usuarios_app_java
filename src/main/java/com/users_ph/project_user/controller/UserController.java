package com.users_ph.project_user.controller;

import com.users_ph.project_user.entities.User;
import com.users_ph.project_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String hello_get(){
        return userService.listar_usuarios().toString();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarUsuario(@PathVariable Integer id) {
        return userService.buscarPorId(id).map(
                ResponseEntity::ok
        ).orElse(
                ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public User criarUsuario(@RequestBody User user){
        return userService.salvarUsuario(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id){
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}