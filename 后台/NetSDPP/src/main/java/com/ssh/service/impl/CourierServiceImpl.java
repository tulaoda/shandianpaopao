package com.ssh.service.impl;

import com.ssh.dao.CourierDao;
import com.ssh.entity.Courier;
import com.ssh.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourierServiceImpl implements CourierService {
    @Autowired
    private CourierDao courierDao;

    public Courier findCourierById(Long id) {
        return courierDao.findCourierById(id);
    }

    public boolean findCourierByOpenid(String openId) {
        return courierDao.findCourierByOpenid(openId);
    }
}
