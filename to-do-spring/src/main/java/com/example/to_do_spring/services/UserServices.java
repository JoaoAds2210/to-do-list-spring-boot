package com.example.to_do_spring.services;
import com.example.to_do_spring.dtos.UserRequest;
import com.example.to_do_spring.entity.User;
import com.example.to_do_spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Getter
@Setter
public class UserServices {

    private final UserRepository repositories;

    //CRUD Sem http
    public User createUser(User users){
        repositories.save(users);
        return users;
    }

    public List<User> findAll (){
        return repositories.findAll();
    }

    public User findById(Long id){
        return repositories.findById(id).orElseThrow(() -> new RuntimeException("Não há usuário com esse ID."));
    }

    public void deleteById(Long id){
        repositories.deleteById(id);
    }

    public User updateUser(Long id, UserRequest updateUser) {
        User user = findById(id);
        user.setUsername(updateUser.getUsername());
        user.setCpf(updateUser.getCpf());
        return repositories.save(user);
    }
}
