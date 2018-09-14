package com.ssh.dao.impl;

import com.ssh.dao.CourierDao;
import com.ssh.entity.Courier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourierDaoImpl implements CourierDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    public Courier findCourierById(Long id){
        return (Courier)getCurrentSession().get(Courier.class,id);
    }
}
