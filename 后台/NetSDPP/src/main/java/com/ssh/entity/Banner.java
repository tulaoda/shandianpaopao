package com.ssh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_banner")
public class Banner {
    @Id
    @GeneratedValue
    private Long id;
    private String imgUrl;
    private Integer imgOrder;

    public Banner() {
    }

    public Banner(String imgUrl, Integer imgOrder) {
        this.imgUrl = imgUrl;
        this.imgOrder = imgOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImgOrder() {
        return imgOrder;
    }

    public void setImgOrder(Integer imgOrder) {
        this.imgOrder = imgOrder;
    }
}
