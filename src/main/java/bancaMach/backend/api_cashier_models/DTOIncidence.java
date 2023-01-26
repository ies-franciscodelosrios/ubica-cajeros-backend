package bancaMach.backend.api_cashier_models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "incidence")
public class DTOIncidence implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private DTOClient client;

    @ManyToOne()
    @JoinColumn(name = "cashier_id")
    private DTOCashier cashier;

    @Column(name = "message")
    private String message;

    public DTOIncidence(Long id, DTOClient client, DTOCashier cashier, String message) {
        this.id = id;
        this.client = client;
        this.cashier = cashier;
        this.message = message;
    }

    public DTOIncidence() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DTOClient getClient() {
        return client;
    }

    public void setClient(DTOClient client) {
        this.client = client;
    }

    public DTOCashier getCashier() {
        return cashier;
    }

    public void setCashier(DTOCashier cashier) {
        this.cashier = cashier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
