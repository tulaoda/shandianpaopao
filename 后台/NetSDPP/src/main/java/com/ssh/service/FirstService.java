package com.ssh.service;

import com.ssh.base.DomainDao;
import com.ssh.entity.Banner;
import com.ssh.entity.First;

import java.util.List;

public interface FirstService extends DomainDao<First, Long> {
    List<First> orderByState(String openId, String state);
    void updateFirstByOrderId(Long orderId,String state);
}
