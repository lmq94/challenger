package app.dto;

import app.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@NoArgsConstructor
@Data
public class UserDTO {

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Size(min = 3, max = 15, message = "el nombre de usuario debe tener entre 3 y 15 caracretes")
    private String username;

    @Email(message = "La direccion de correo debe tener un formato valido" )
    private String email;

    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
