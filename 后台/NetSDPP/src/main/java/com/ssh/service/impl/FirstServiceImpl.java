package com.ssh.service.impl;

import com.ssh.dao.FirstDao;
import com.ssh.entity.First;
import com.ssh.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FirstServiceImpl implements FirstService {
    @Autowired
    private FirstDao firstDao;

    public First load(Long id) {
        return null;
    }

    public First get(Long id) {
        return null;
    }

    public List<First> findAll() {
        return null;
    }

    public void persist(First entity) {

    }

    public Long save(First entity) {
        return firstDao.save(entity);
    }

    public void saveOrUpdate(First entity) {
        firstDao.saveOrUpdate(entity);
    }

    public void delete(Long id) {

    }

    public void flush() {

    }

    public void updateFirstStateByOrderId(Long orderId, String state) {
        firstDao.updateFirstStateByOrderId(orderId, state);
    }

    public List<First> orderByState(String openId, String state, int page, int pageSize) {
        return firstDao.orderByState(openId, state, page, pageSize);
    }

    public Long findFirstIdByOrderId(Long orderId) {
        return firstDao.findFirstIdByOrderId(orderId);
    }

    public First findFirstByOrderId(Long orderId) {
        return firstDao.findFirstByOrderId(orderId);
    }

    public First findFirstById(Long id) {
        return firstDao.findFirstById(id);
    }

    public Double getPrice(Long orderId) {
        return firstDao.getPrice(orderId);
    }
}
