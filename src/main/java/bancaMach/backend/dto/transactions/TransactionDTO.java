package bancaMach.backend.dto.transactions;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionDTO implements Serializable {

    private static final long serialVersion = 1L;

    private Long id;
    private Long client;
    private Long cashier;
    private Boolean type;
    private Double amount;
    private String securityCode;
    private LocalDateTime init_date;
    private LocalDateTime end_date;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, Long client, Long cashier, Boolean type, Double amount, String securityCode, LocalDateTime init_date, LocalDateTime end_date) {
        this.id = id;
        this.client = client;
        this.cashier = cashier;
        this.type = type;
        this.amount = amount;
        this.securityCode = securityCode;
        this.init_date = init_date;
        this.end_date = end_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getCashier() {
        return cashier;
    }

    public void setCashier(Long cashier) {
        this.cashier = cashier;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public LocalDateTime getInit_date() {
        return init_date;
    }

    public void setInit_date(LocalDateTime init_date) {
        this.init_date = init_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }
}
