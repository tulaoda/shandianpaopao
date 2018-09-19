package com.ssh.service.impl;

import com.ssh.dao.PriceDao;
import com.ssh.entity.Price;
import com.ssh.entity.User;
import com.ssh.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceDao priceDao;

    public Price load(Long id) {
        return null;
    }

    public Price get(Long id) {
        return null;
    }

    public List<Price> findAll() {
        return priceDao.findAll();
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
