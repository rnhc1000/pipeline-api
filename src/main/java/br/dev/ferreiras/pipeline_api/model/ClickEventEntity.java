package br.dev.ferreiras.pipeline_api.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Map;

@Entity
public class ClickEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant timestamp;
    private String buttonId;
    private String userAgent;
    private String referer;
    private String clientIp;
    private String countryCode;
    private String city;
    private String region;
    private Double latitude;
    private Double longitude;

    @ElementCollection
    @CollectionTable(name = "click_event_cookies", joinColumns = @JoinColumn(name = "click_event_id"))
    @MapKeyColumn(name = "cookie_key")
    @Column(name = "cookie_value")
    private Map<String, String> cookieData;

    @Embedded
    private FormDataEmbeddable formData;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Map<String, String> getCookieData() {
        return cookieData;
    }

    public void setCookieData(Map<String, String> cookieData) {
        this.cookieData = cookieData;
    }

    public FormDataEmbeddable getFormData() {
        return formData;
    }

    public void setFormData(FormDataEmbeddable formData) {
        this.formData = formData;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

