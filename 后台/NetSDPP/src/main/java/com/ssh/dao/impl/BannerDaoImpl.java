package com.ssh.dao.impl;

import com.ssh.dao.BannerDao;
import com.ssh.entity.Banner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerDaoImpl implements BannerDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Banner load(Long id) {
        return (Banner) getCurrentSession().load(Banner.class, id);
    }

    public Banner get(Long id) {
        return (Banner) getCurrentSession().load(Banner.class, id);
    }

    public List<Banner> findAll() {
        String hql = "from Banner";
        return getCurrentSession().createQuery(hql).list();
    }

    public void persist(Banner entity) {
        getCurrentSession().persist(entity);
    }

    public Long save(Banner entity) {
        return (Long) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Banner entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Long id) {
        Banner banner = load(id);
        getCurrentSession().delete(banner);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
