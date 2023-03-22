package bancaMach.backend.dto.transactions;

import java.io.Serializable;

public class TransactionRequestDTO implements Serializable {

    private static final long serialVersion = 1L;
    private Long atmId;
    private String securityCode;

    public TransactionRequestDTO() {}

    public TransactionRequestDTO(Long atmId, String securityCode) {
        this.atmId = atmId;
        this.securityCode = securityCode;
    }

    public Long getAtmId() {
        return atmId;
    }

    public void setAtmId(Long atmId) {
        this.atmId = atmId;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }


}