package bancaMach.backend.api_cashier_models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.locationtech.jts.geom.Point;
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
    private String photo;

    @Column(name="address", length = 100)
    private String address;

    @Column(name="locality", length = 50)
    private String locality;

    @Column(name="cp", length = 5)
    private String cp;

    @JsonIgnore
    @Column(name = "position")
    private Point position;

    @Transient
    @Column(name="lattitude")
    private Double lattitude;

    @Transient
    @Column(name="longitude")
    private Double longitude;

    @Column(name="balance")
    private Double balance;

    @Column(name="available")
    private Boolean available;

    @JsonManagedReference
    @OneToMany(mappedBy = "cashier")
    private List<DTOTransaction> transactions;

    @JsonManagedReference
    @OneToMany(mappedBy = "cashier")
    List<DTOIncidence> incidences;

    public DTOCashier(Long id, String photo, String address, String locality, String cp, Point position, Double lattitude, Double longitude,
                      Double balance, Boolean available, List<DTOTransaction> transactions, List<DTOIncidence> incidences) {
        this.id = id;
        this.photo = photo;
        this.address = address;
        this.locality = locality;
        this.cp = cp;
        this.position = position;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String location) {
        this.locality = location;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
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
