package bancaMach.backend.api_cashier_models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cashier")
public class DTOCashier implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="photo")
    private Byte[] photo;

    @Column(name="address", length = 100)
    private String address;

    @Column(name="location", length = 50)
    private String location;

    @Column(name="cp", length = 5)
    private Integer cp;

    @Column(name="lattitude")
    private Double lattitude;

    @Column(name="longitude")
    private Double longitude;

    @Column(name="balance")
    private Double balance;

    @Column(name="available")
    private Boolean available;

    @OneToMany(mappedBy = "cashier")
    private List<DTOTransaction> transactions;
    @OneToMany(mappedBy = "cashier")
    List<DTOIncidence> incidences;

    public DTOCashier(Long id, Byte[] photo, String address, String location, Integer cp, Double lattitude, Double longitude,
                      Double balance, Boolean available, List<DTOTransaction> transactions, List<DTOIncidence> incidences) {
        this.id = id;
        this.photo = photo;
        this.address = address;
        this.location = location;
        this.cp = cp;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.balance = balance;
        this.available = available;
        this.transactions = transactions;
        this.incidences = incidences;
    }

    public DTOCashier() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTOCashier that = (DTOCashier) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
