package br.dev.ferreiras.pipeline_api.dto;

import java.time.Instant;
import java.util.Map;

public class ClickEventDto {

    private Instant timestamp;
    private String buttonId;
    private String userAgent;
    private Map<String, String> cookieData;
    private FormDataDto formData;
    private String referer;
    private String clientIp;
    private String countryCode;
    private String city;
    private String region;
    private Double latitude;
    private Double longitude;

    public ClickEventDto() {
    }

    public ClickEventDto(Instant timestamp, String buttonId, String userAgent, Map<String, String> cookieData,
                         FormDataDto formData, String referer) {
        this.timestamp = timestamp;
        this.buttonId = buttonId;
        this.userAgent = userAgent;
        this.cookieData = cookieData;
        this.formData = formData;
        this.referer = referer;
    }
// Getters and Setters

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

    public FormDataDto getFormData() {
        return formData;
    }

    public void setFormData(FormDataDto formData) {
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

    @Override
    public String toString() {
        return "ClickEventDto{" +
                "timestamp=" + timestamp +
                ", buttonId='" + buttonId + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", cookieData=" + cookieData +
                ", formData=" + formData +
                '}';
    }
}
