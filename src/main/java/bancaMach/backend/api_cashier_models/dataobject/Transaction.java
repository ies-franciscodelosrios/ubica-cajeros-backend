package bancaMach.backend.api_cashier_models.dataobject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private Cashier cashier;

    @Column(name = "security_code")
    private String securityCode;

    @Column(name = "init_timedate")
    private LocalDateTime init_date;

    @Column(name = "end_timedate")
    private LocalDateTime end_date;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    private Boolean type;

    @Column(name = "finished")
    private Boolean finished;

    public Transaction(Long id, Client client, Cashier cashier, String securityCode, LocalDateTime init_date,
                       LocalDateTime end_date, Double amount, Boolean type, Boolean finished) {
        this.id = id;
        this.client = client;
        this.cashier = cashier;
        this.securityCode = securityCode;
        this.init_date = init_date;
        this.end_date = end_date;
        this.amount = amount;
        this.type = type;
        this.finished = finished;
    }

    public Transaction(Client client, Cashier cashier, String securityCode, LocalDateTime init_date,
                       LocalDateTime end_date, Double amount, Boolean type, Boolean finished) {
        this.client = client;
        this.cashier = cashier;
        this.securityCode = securityCode;
        this.init_date = init_date;
        this.end_date = end_date;
        this.amount = amount;
        this.type = type;
        this.finished = finished;
    }

    public Transaction() {}

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
