package com.ssh.entity;


import com.ssh.utils.CreateOrderID;

import javax.persistence.*;

@Entity
@Table(name = "t_first")
public class First {
    @Id
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
    private Double price;
    private String createTime;
    private String payTime;
    private String receiptTime;
    private String courierId;
    private String courierName;
    private String courierTel;

    public First() {

    }

    public First(Long orderId, String openId, String receiver, String telephone, String address, String information, String size, String deliveryTime, String classification, String receiveType, String state, Double price, String createTime, String payTime, String receiptTime, String courierId, String courierName, String courierTel) {
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
        this.receiptTime = receiptTime;
        this.courierId = courierId;
        this.courierName = courierName;
        this.courierTel = courierTel;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public String getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(String receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getCourierTel() {
        return courierTel;
    }

    public void setCourierTel(String courierTel) {
        this.courierTel = courierTel;
    }
}
