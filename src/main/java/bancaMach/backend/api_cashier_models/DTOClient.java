package bancaMach.backend.api_cashier_models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "client")
public class DTOClient implements Serializable {

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
    List<DTOTransaction> transactions;


    @JsonIgnore
    //@JsonManagedReference
    @OneToMany(mappedBy = "client")
    List<DTOIncidence> incidences;

    public DTOClient() {
    }
    public DTOClient(Long id, String account, List<DTOTransaction> transactions, List<DTOIncidence> incidences) {
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
    public List<DTOTransaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<DTOTransaction> transactions) {
        this.transactions = transactions;
    }
    public List<DTOIncidence> getIncidences() {
        return incidences;
    }
    public void setIncidences(List<DTOIncidence> incidences) {
        this.incidences = incidences;
    }
}
