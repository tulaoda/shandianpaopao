package com.ssh.service;

import com.ssh.entity.Courier;

public interface CourierService {
    Courier findCourierById(Long id);
    boolean findCourierByOpenid(String openId);
}
