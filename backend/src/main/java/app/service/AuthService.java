package app.service;

import app.authJwt.JwtTokenProvider;
import app.domain.User;
import app.dto.UserDTO;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String authUser(String email, String password) throws AuthenticationException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Correo electrónico o contraseña incorrectos"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Correo electrónico o contraseña incorrectos");
        }

        UserDTO userDate = new UserDTO(user);

        return jwtTokenProvider.generateToken(userDate);
    }


}