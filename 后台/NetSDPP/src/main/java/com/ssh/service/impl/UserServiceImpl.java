package com.ssh.service.impl;

import com.ssh.dao.UserDao;
import com.ssh.entity.User;
import com.ssh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


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
        return userDao.save(entity);
    }

    public void saveOrUpdate(User entity) {
        userDao.saveOrUpdate(entity);
    }

    public void delete(Long id) {

    }

    public void flush() {

    }

    public User getUserByOpenId(String openId) {
        return userDao.getUserByOpenId(openId);
    }
}
