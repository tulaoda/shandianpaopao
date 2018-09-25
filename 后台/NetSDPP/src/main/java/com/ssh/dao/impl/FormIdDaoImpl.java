package com.ssh.dao.impl;

import com.ssh.dao.FormIdDao;
import com.ssh.entity.First;
import com.ssh.entity.FormId;
import com.ssh.entity.Price;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormIdDaoImpl implements FormIdDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }


    public FormId load(Long id) {
        return null;
    }

    public FormId get(Long id) {
        return (FormId) getCurrentSession().get(FormId.class, id);
    }

    public List<FormId> findAll() {
        return null;
    }

    public void persist(FormId entity) {

    }

    public Long save(FormId entity) {
        return null;
    }

    public void saveOrUpdate(FormId entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Long id) {

    }

    public void flush() {

    }

    public FormId findByOpenId(String openId) {
        String hql = "from FormId where openId=?";
        return (FormId) getCurrentSession().createQuery(hql).setString(0, openId).uniqueResult();
    }
}
