package bancaMach.backend.api_cashier_dto.users;

import java.io.Serializable;

public class ResponsePasswordDTO implements Serializable {

    private static final long serialVersion = 1L;
    private String code;

    public ResponsePasswordDTO() {
    }
    public ResponsePasswordDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
