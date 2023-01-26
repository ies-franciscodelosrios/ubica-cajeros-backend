package bancaMach.backend.api_cashier_models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class DTOTransaction implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private DTOClient client;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private DTOCashier cashier;

    @Column(name = "qr")
    private Byte[] qr;

    @Column(name = "init_date")
    private LocalDateTime init_date;

    @Column(name = "end_date")
    private LocalDateTime end_date;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    private String type;

    @Column(name = "entity")
    private String entity;

    public DTOTransaction(Long id, DTOClient client, DTOCashier cashier, Byte[] qr, LocalDateTime init_date,
                          LocalDateTime end_date, Double amount, String type, String entity) {
        this.id = id;
        this.client = client;
        this.cashier = cashier;
        this.qr = qr;
        this.init_date = init_date;
        this.end_date = end_date;
        this.amount = amount;
        this.type = type;
        this.entity = entity;
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

    public Byte[] getQr() {
        return qr;
    }

    public void setQr(Byte[] qr) {
        this.qr = qr;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
