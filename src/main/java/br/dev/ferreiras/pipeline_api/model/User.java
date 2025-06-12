package br.dev.ferreiras.pipeline_api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String address;
    private String country;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<MetaData> metadata;

    public User() {
    }

    public User(Long id, String name, String email, String address, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setMetadata(List<MetaData> metadata) {
        this.metadata = metadata;
    }

    public List<MetaData> getMetadata() {
        return metadata;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
