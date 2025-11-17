package com.example.to_do_spring.services;
import com.example.to_do_spring.dtos.UserRequest;
import com.example.to_do_spring.entity.User;
import com.example.to_do_spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Getter
@Setter
public class UserServices {

    private final UserRepository repositories;
    private final PasswordEncoder encoder;
    //validação de negocio//
    private void validateUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("O nome não pode ser nulo.");
        }

        if (!username.matches(".*[A-Z].*")) {
            throw new RuntimeException("O nome deve conter ao menos uma letra maiúscula.");
        }

        if (!username.matches(".*[0-9].*")) {
            throw new RuntimeException("O nome deve conter ao menos um número.");
        }
    }

    private void validatePassword(String senha) {

        if (senha == null || senha.length() < 4) {
            throw new RuntimeException("A senha deve ter no mínimo 4 caracteres.");
        }

        // aceita letras, números e caracteres especiais
        if (!senha.matches("^[a-zA-Z0-9!@#$%^&*()_+=\\-{}\\[\\]:\";'<>?,./]+$")) {
            throw new RuntimeException("A senha contém caracteres inválidos.");
        }
    }

    //CRUD Sem http
    public User createUser(UserRequest request){

        validateUsername(request.username());
        validatePassword(request.senha());

        if (repositories.findByCpf(request.cpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setCpf(request.cpf());
        user.setSenha(encoder.encode(request.senha()));

        return repositories.save(user);
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
        user.setUsername(updateUser.username());
        user.setCpf(updateUser.cpf());
        return repositories.save(user);
    }
}
