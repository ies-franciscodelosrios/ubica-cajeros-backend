package bancaMach.backend.api_cashier_dto.atm;
public class ATMSaveDTO {
    private Long id;
    private String address;
    private Boolean available;
    private Double balance;
    private String cp;
    private String locality;
    private String photo;
    private Double lattitude;
    private Double longitude;
    public ATMSaveDTO(Long id, String address, Boolean available, Double balance, String cp, String locality, String photo, Double lattitude, Double longitude) {
        this.address = address;
        this.available = available;
        this.balance = balance;
        this.cp = cp;
        this.locality = locality;
        this.photo = photo;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }
    public ATMSaveDTO() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public String getCp() {
        return cp;
    }
    public void setCp(String cp) {
        this.cp = cp;
    }
    public String getLocality() { return locality; }
    public void setLocality(String locality) {
        this.locality = locality;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
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
}
