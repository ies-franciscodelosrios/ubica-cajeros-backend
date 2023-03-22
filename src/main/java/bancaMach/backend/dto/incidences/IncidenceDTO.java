package bancaMach.backend.dto.incidences;

import java.io.Serializable;

public class IncidenceDTO implements Serializable {

    private static final long serialVersion = 1L;

    private Long client;
    private Long cashier;
    private String message;

    public IncidenceDTO() {
    }

    public IncidenceDTO(Long client, Long cashier, String message) {
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
