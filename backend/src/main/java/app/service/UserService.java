package app.service;

import app.domain.User;
import app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import app.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long id) {
        Optional<User> userSearch = this.userRepository.findById(id);
        return userSearch
                .map(UserDTO::new)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
    }

    public void createUser(UserDTO user) {
        Optional<User> existingUser = this.userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new DataIntegrityViolationException("Ya existe un usuario con el correo: " + user.getEmail());
        }

        try {
            User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());
            this.userRepository.save(newUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al crear el usuario: " + user.getUsername());
        }
    }

    public void updateUser(UserDTO user, Long id) {
        Optional<User> userSearch = this.userRepository.findById(id);
        if (userSearch.isPresent()) {
            User userUpdate = userSearch.get();
            userUpdate.setEmail(user.getEmail());
            userUpdate.setUsername(user.getUsername());
            this.userRepository.save(userUpdate);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con id: " + id);
        }
    }
}




