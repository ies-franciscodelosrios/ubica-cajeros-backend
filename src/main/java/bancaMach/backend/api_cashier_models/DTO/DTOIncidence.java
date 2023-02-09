package bancaMach.backend.api_cashier_models.DTO;

public class DTOIncidence {

    private Long client;
    private Long cashier;
    private String message;

    public DTOIncidence() {
    }
    public DTOIncidence(Long client, Long cashier, String message) {
        this.client = client;
        this.cashier = cashier;
        this.message = message;
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
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
