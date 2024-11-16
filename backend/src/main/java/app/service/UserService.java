package app.service;


import app.domain.User;
import app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDTO getUserById(Long id){
        Optional<User> userSearch = this.userRepository.findById(id);
        return userSearch.map(UserDTO::new).orElse(null);
    }

    public void createUser(UserDTO user){
        User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());
        this.userRepository.save(newUser);

    }

    public void updateUser(UserDTO user, Long id){
        Optional<User> userSearch = this.userRepository.findById(id);
        if(userSearch.isPresent()){
           User userUpdate = userSearch.get();
           userUpdate.setEmail(user.getEmail());
           userUpdate.setUsername(user.getUsername());
           this.userRepository.save(userUpdate);
        }
    }


}
