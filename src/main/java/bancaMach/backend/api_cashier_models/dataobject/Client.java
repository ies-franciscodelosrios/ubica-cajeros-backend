package bancaMach.backend.api_cashier_models.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account")
    private String account;

    @JsonIgnore
    //@JsonManagedReference
    @OneToMany(mappedBy = "client")
    List<Transaction> transactions;


    @JsonIgnore
    //@JsonManagedReference
    @OneToMany(mappedBy = "client")
    List<Incidence> incidences;

    public Client() {
    }
    public Client(Long id, String account, List<Transaction> transactions, List<Incidence> incidences) {
        this.id = id;
        this.account = account;
        this.transactions = transactions;
        this.incidences = incidences;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public List<Incidence> getIncidences() {
        return incidences;
    }
    public void setIncidences(List<Incidence> incidences) {
        this.incidences = incidences;
    }
}
