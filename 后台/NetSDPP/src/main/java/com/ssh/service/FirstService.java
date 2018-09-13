package com.ssh.service;

import com.ssh.base.DomainDao;
import com.ssh.entity.Banner;
import com.ssh.entity.First;

import java.util.List;

public interface FirstService extends DomainDao<First, Long> {
    void updateFirstStateByOrderId(Long orderId, String state)throws Exception;

    List<First> orderByState(String openId, String state,int page,int pageSize)throws Exception;

    Long findFirstIdByOrderId(Long orderId)throws Exception;

    First findFirstByOrderId(Long orderId)throws Exception;

    First findFirstById(Long id)throws Exception;
}
