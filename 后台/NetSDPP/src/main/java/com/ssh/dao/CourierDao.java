package com.ssh.dao;

import com.ssh.entity.Courier;

public interface CourierDao {
    Courier findCourierById(Long id);

    boolean findCourierByOpenid(String openId);
}
