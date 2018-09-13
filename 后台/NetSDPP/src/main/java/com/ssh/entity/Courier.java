package com.ssh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_courier")
public class Courier {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String telephone;
    private String courierOpenId;

    public Courier(Long id, String name, String telephone, String courierOpenId) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.courierOpenId = courierOpenId;
    }

    public Courier() {
    }

    public Long getId() {
        return id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCourierOpenId() {
        return courierOpenId;
    }

    public void setCourierOpenId(String courierOpenId) {
        this.courierOpenId = courierOpenId;
    }
}
