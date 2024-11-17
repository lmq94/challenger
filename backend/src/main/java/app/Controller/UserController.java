package app.Controller;

import app.dto.UserDTO;
import app.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.UserService;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO user) {
        this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("se ha creado con exito el usuario");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) throws AuthenticationException {

            String token = authService.authUser(user.getEmail(), user.getPassword());

            return ResponseEntity.ok(token);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO user, @PathVariable Long id) {
        this.userService.updateUser(user,id);
        return ResponseEntity.ok("Se ha actualizado con exito los datos del usuario!");
    }

}