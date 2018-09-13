package com.ssh.dao.impl;

import com.ssh.dao.FirstDao;
import com.ssh.entity.First;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        String hql = "from First order by id desc";
        return getCurrentSession().createQuery(hql).list();
    }

    public void persist(First entity) {
        getCurrentSession().persist(entity);
    }

    public Long save(First entity) {
        return (Long) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(First entity) {
        getCurrentSession().update(entity);
    }

    public void delete(Long id) {
        First first = load(id);
        getCurrentSession().delete(first);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public void updateFirstStateByOrderId(Long orderId, String state) throws Exception{
        String hql = "UPDATE First f SET f.state=? WHERE f.orderId=?";
        getCurrentSession().createQuery(hql).setString(0, state).setLong(1, orderId).executeUpdate();
    }

    public List<First> orderByState(String openId, String state, int page, int pageSize) throws Exception{
        String hql = "from First where openId=? and state=? order by id desc";
        return getCurrentSession().createQuery(hql).setString(0, openId).setString(1, state).
                setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize).list();
    }

    public Long findFirstIdByOrderId(Long orderId) throws Exception{
        String hql = "SELECT id FROM First WHERE orderId=?";
        return (Long) getCurrentSession().createQuery(hql).setLong(0, orderId).uniqueResult();
    }

    public First findFirstByOrderId(Long orderId) throws Exception{
        String hql = "FROM First WHERE orderId=?";
        return (First) getCurrentSession().createQuery(hql).setLong(0, orderId).uniqueResult();
    }

    public First findFirstById(Long id) throws Exception{
        return (First) getCurrentSession().load(First.class, id);
    }
}
