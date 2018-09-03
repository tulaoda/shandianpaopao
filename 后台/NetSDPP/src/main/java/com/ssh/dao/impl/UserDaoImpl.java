package com.ssh.dao.impl;

import com.ssh.dao.UserDao;
import com.ssh.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public User load(Long id) {
        return null;
    }

    public User get(Long id) {
        return null;
    }

    public List<User> findAll() {
        return null;
    }

    public void persist(User entity) {

    }

    public Long save(User entity) {
        return (Long) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(User entity) {

    }

    public void delete(Long id) {

    }

    public void flush() {

    }
}
