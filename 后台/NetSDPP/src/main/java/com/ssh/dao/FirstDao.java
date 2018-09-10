package com.ssh.dao;

import com.ssh.base.DomainDao;
import com.ssh.entity.First;

public interface FirstDao extends DomainDao<First, Long> {
    void updateFirstStateByOrderId(Long orderId, String state);
}
