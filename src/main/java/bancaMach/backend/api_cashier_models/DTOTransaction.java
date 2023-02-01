package bancaMach.backend.api_cashier_models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class DTOTransaction implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private DTOClient client;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private DTOCashier cashier;

    @Column(name = "security_code")
    private String secutityCode;

    @Column(name = "init_timedate")
    private LocalDateTime init_date;

    @Column(name = "end_timedate")
    private LocalDateTime end_date;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    private Boolean type;


    public DTOTransaction(Long id, DTOClient client, DTOCashier cashier, String  secutityCode, LocalDateTime init_date,
                          LocalDateTime end_date, Double amount, Boolean type) {
        this.id = id;
        this.client = client;
        this.cashier = cashier;
        this.secutityCode = secutityCode;
        this.init_date = init_date;
        this.end_date = end_date;
        this.amount = amount;
        this.type = type;
    }

    public DTOTransaction() {}

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

    public String getSecutityCode() {
        return secutityCode;
    }

    public void setSecutityCode(String secutityCode) {
        this.secutityCode = secutityCode;
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
}
