package br.dev.ferreiras.pipeline_api.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_cookie")
public class MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cookieKey;

    private String cookieValue;

    private Instant timeStamp;

    private String buttonId;

    private String userAgent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public MetaData() {
    }

    public MetaData(Long id, String cookieKey, String cookieValue, Instant timeStamp, String buttonId, String userAgent) {
        this.id = id;
        this.cookieKey = cookieKey;
        this.cookieValue = cookieValue;
        this.timeStamp = timeStamp;
        this.buttonId = buttonId;
        this.userAgent = userAgent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
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


    public String getCookieKey() {
        return cookieKey;
    }

    public void setCookieKey(String cookieKey) {
        this.cookieKey = cookieKey;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MetaData metaData = (MetaData) o;
        return id == metaData.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
