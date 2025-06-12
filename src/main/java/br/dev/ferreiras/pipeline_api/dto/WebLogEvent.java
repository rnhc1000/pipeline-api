package br.dev.ferreiras.pipeline_api.dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WebLogEvent {
    private Instant timestamp;
    private String userAgent;
    private String buttonId;
    private List<MetaDataDto> cookieData;
    private List<UserDataDto> formData;

    public WebLogEvent(Instant timestamp, String userAgent, String buttonId,
                       List<MetaDataDto> cookieData, List<UserDataDto> formData) {
        this.timestamp = timestamp;
        this.userAgent = userAgent;
        this.buttonId = buttonId;
        this.cookieData = cookieData;
        this.formData = formData;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getButtonId() {
        return buttonId;
    }

    public List<MetaDataDto> getCookieData() {
        return cookieData;
    }

    public List<UserDataDto> getFormData() {
        return formData;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public void setCookieData(List<MetaDataDto> cookieData) {
        this.cookieData = cookieData;
    }

    public void setFormData(List<UserDataDto> formData) {
        this.formData = formData;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WebLogEvent) obj;
        return Objects.equals(this.timestamp, that.timestamp) &&
                Objects.equals(this.userAgent, that.userAgent) &&
                Objects.equals(this.buttonId, that.buttonId) &&
                Objects.equals(this.cookieData, that.cookieData) &&
                Objects.equals(this.formData, that.formData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, userAgent, buttonId, cookieData, formData);
    }

    @Override
    public String toString() {
        return "WebLogEvent[" +
                "timestamp=" + timestamp + ", " +
                "userAgent=" + userAgent + ", " +
                "buttonId=" + buttonId + ", " +
                "cookieData=" + cookieData + ", " +
                "formData=" + formData + ']';
    }

}