package com.ssh.entity;


import com.ssh.utils.CreateOrderID;

import javax.persistence.*;

@Entity
@Table(name = "t_first")
public class First {
    @Id
    @GeneratedValue
    private Long id;
    private Long orderId;
    private String openId;
    private String receiver;
    private String telephone;
    private String address;
    private String information;
    private String size;
    private String deliveryTime;
    private String classification;
    private String receiveType;
    private String state;
    private String price;
    private String createTime;
    private String payTime;

    public First() {

    }

    public First(Long orderId, String openId, String receiver, String telephone, String address, String information, String size, String deliveryTime, String classification, String receiveType, String state, String price, String createTime, String payTime) {
        this.orderId = orderId;
        this.openId = openId;
        this.receiver = receiver;
        this.telephone = telephone;
        this.address = address;
        this.information = information;
        this.size = size;
        this.deliveryTime = deliveryTime;
        this.classification = classification;
        this.receiveType = receiveType;
        this.state = state;
        this.price = price;
        this.createTime = createTime;
        this.payTime = payTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
