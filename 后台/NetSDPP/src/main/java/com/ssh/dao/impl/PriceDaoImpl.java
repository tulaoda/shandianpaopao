package com.ssh.dao.impl;

import com.ssh.dao.PriceDao;
import com.ssh.entity.Price;
import com.ssh.entity.Price;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PriceDaoImpl implements PriceDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<Price> findAll() {
        String hql = "SELECT price from Price";
        return getCurrentSession().createQuery(hql).list();
    }

    public Price load(Long id) {
        return null;
    }

    public Price get(Long id) {
        return null;
    }


    public void persist(Price entity) {

    }

    public Long save(Price entity) {
        return null;
    }

    public void saveOrUpdate(Price entity) {

    }

    public void delete(Long id) {

    }

    public void flush() {

    }
}
