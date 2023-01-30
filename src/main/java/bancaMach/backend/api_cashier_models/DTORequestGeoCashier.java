package bancaMach.backend.api_cashier_models;

import java.io.Serializable;

public class DTORequestGeoCashier implements Serializable {
    private static final long serialVersion = 1L;
    private Long user;
    private Double lat;
    private Double lng;

    public DTORequestGeoCashier() {
    }

    public DTORequestGeoCashier(Long user, Double lat, Double lng) {

        this.user = user;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "DTORequestGeoCashier{" +
                "user=" + user +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
