package bancaMach.backend.dto.atm;

import java.io.Serializable;

public class AtmDAO implements Serializable {

    private static final long serialVersion = 1L;

    private Long user;
    private Double lat;
    private Double lng;
    private Integer distance;

    public AtmDAO() {
    }

    public AtmDAO(Long user, Double lat, Double lng, Integer distance) {
        this.user = user;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
