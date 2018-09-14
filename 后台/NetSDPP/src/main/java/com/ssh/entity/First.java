package com.ssh.entity;


import com.ssh.utils.CreateOrderID;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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
    private String price;
    private Date createtime;
    private String payTime;
    private String receiptTime;
    private Long courierId;

    public First() {

    }

    public First(Long orderId, String openId, String receiver, String telephone, String address, String information, String size, String deliveryTime, String classification, String receiveType, String state, String price, Date createtime, String payTime, String receiptTime, Long courierId) {
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
        this.createtime = createtime;
        this.payTime = payTime;
        this.receiptTime = receiptTime;
        this.courierId = courierId;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }
}
