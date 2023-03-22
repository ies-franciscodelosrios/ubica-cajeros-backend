package bancaMach.backend.api_cashier_dto.users;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersion = 1L;
    private Long id;
    private String dni;
    private String account;
    private String password;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String dni, String account, String password, String email) {
        this.dni = dni;
        this.account = account;
        this.password = password;
        this.email = email;
    }

    public UserDTO(Long id, String dni, String account, String password, String email) {
        this.id = id;
        this.dni = dni;
        this.account = account;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
