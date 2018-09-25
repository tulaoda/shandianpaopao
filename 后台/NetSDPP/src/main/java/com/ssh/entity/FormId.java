package com.ssh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_formId")
public class FormId {
    @Id
    @GeneratedValue
    @GenericGenerator(name = "openId",strategy = "assigned")
    public String openId;
    private String formId;

    public FormId() {
    }

    public FormId(String openId, String formId) {
        this.openId = openId;
        this.formId = formId;
    }



    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
