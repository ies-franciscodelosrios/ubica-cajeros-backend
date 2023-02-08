package bancaMach.backend.api_cashier_models.DTO;

public class DTOTransaction {

    private Long client;
    private Long cashier;
    private Boolean type;
    private Double amount;

    public DTOTransaction(Long client, Long cashier, Boolean type, Double amount) {
        this.client = client;
        this.cashier = cashier;
        this.type = type;
        this.amount = amount;
    }

    public DTOTransaction() {}

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
}
