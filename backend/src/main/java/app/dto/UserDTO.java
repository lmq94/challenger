package app.dto;

import app.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Size(min = 3, max = 15, message = "el nombre de usuario debe tener entre 3 y 15 caracretes")
    private String username;

    @Email(message = "La direccion de correo debe tener un formato valido" )
    private String email;

    @Size(min = 8, message = "La contraseña debe tener mas de 8 caracteres")
    private String password;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
