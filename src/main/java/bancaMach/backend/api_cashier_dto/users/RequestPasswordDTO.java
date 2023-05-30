package bancaMach.backend.api_cashier_dto.users;

import java.io.Serializable;

public class RequestPasswordDTO implements Serializable {

    private static final long serialVersion = 1L;
    private String dni;
    private String email;
    private String subject;
    private String message;

    public RequestPasswordDTO() {
    }
    public RequestPasswordDTO(String dni, String email, String subject, String message) {
        this.dni = dni;
        this.email = email;
        this.subject = subject;
        this.message = message;
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
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
