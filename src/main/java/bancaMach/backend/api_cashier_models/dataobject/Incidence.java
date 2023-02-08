package bancaMach.backend.api_cashier_models.dataobject;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "incidence")
public class Incidence implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "cashier_id")
    private Cashier cashier;

    @Column(name = "message")
    private String message;

    public Incidence(Long id, Client client, Cashier cashier, String message) {
        this.id = id;
        this.client = client;
        this.cashier = cashier;
        this.message = message;
    }

    public Incidence() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
