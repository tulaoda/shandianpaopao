package com.ssh.entity;


import javax.persistence.*;


@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "openid")
    private String openid;
    @Column(name = "name")
    private String name;
    @Column(name = "school")
    private String school;
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
    private String formId;

    public User() {

    }

    public User(String openid, String name, String school, String address, String telephone, String formId) {
        this.openid = openid;
        this.name = name;
        this.school = school;
        this.address = address;
        this.telephone = telephone;
        this.formId = formId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
