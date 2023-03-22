package bancaMach.backend.dto.transactions;

import java.io.Serializable;

public class TransactionResponseDTO implements Serializable {

    private static final long serialVersion = 1L;
    private String status;
    private Double amount;

    public TransactionResponseDTO() {}

    public TransactionResponseDTO(String error, Double amount) {
        this.status = error;
        this.amount = amount;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
