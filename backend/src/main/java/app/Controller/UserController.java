package app.Controller;

import app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("se ha creado con exito el usuario");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user, @PathVariable Long id) {
        this.userService.updateUser(user,id);
        return ResponseEntity.ok("Se ha actualizado con exito los datos del usuario!");
    }

}