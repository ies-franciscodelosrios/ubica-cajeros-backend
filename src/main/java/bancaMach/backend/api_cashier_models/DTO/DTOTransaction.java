package bancaMach.backend.api_cashier_models.DTO;

public class DTOTransaction {

    private Long user;
    private Long cashier;
    private Boolean type;
    private Double amount;

    public DTOTransaction(Long user, Long cashier, Boolean type, Double amount) {
        this.user = user;
        this.cashier = cashier;
        this.type = type;
        this.amount = amount;
    }

    public DTOTransaction() {}

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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
