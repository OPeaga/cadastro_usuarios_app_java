package com.users_ph.project_user.service;

import com.users_ph.project_user.entities.User;
import com.users_ph.project_user.exceptions.RecursoNaoEncontradoException;
import com.users_ph.project_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listar_usuarios(){
        return userRepository.findAll();
    }

    public User buscarPorId(Integer id){
        return userRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado")
        );
    }

    public User salvarUsuario(User user){
        return userRepository.save(user);
    }

    public void deletarUsuario(Integer id){

        if (!userRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado para deletar.");
        }

        userRepository.deleteById(id);
    }
}
