package bancaMach.backend.api_cashier_dto.users;

import java.io.Serializable;

public class ResponsePasswordDTO implements Serializable {

    private static final long serialVersion = 1L;
    private String responseMessage;

    public ResponsePasswordDTO() {
    }
    public ResponsePasswordDTO(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
