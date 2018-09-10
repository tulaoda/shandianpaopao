package com.ssh.dao.impl;

import com.ssh.dao.FirstDao;
import com.ssh.entity.First;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirstDaoImpl implements FirstDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public First load(Long id) {
        return (First) getCurrentSession().load(First.class, id);
    }

    public First get(Long id) {
        return (First) getCurrentSession().load(First.class, id);
    }

    public List<First> findAll() {
        String hql = "from First";
        return getCurrentSession().createQuery(hql).list();
    }

    public void persist(First entity) {
        getCurrentSession().persist(entity);
    }

    public Long save(First entity) {
        return (Long) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(First entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Long id) {
        First first = load(id);
        getCurrentSession().delete(first);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public void updateFirstStateByOrderId(Long orderId, String state) {
        String hql = "update First set state=? where orderId=?";
        getCurrentSession().createQuery(hql).setString(0, state).setLong(1, orderId).executeUpdate();
    }
}
