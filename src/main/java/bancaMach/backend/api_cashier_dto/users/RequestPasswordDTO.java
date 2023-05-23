package bancaMach.backend.api_cashier_dto.users;

import java.io.Serializable;

public class RequestPasswordDTO implements Serializable {

    private static final long serialVersion = 1L;
    private String dni;
    private String email;

    public RequestPasswordDTO() {
    }
    public RequestPasswordDTO(String dni, String email) {
        this.dni = dni;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
