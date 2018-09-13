package com.ssh.dao;

import com.ssh.base.DomainDao;
import com.ssh.entity.First;

import java.util.List;


public interface FirstDao extends DomainDao<First, Long> {
    //通过orderID修改订单状态
    void updateFirstStateByOrderId(Long orderId, String state)throws Exception;
    //按订单状态分页查询订单
    List<First> orderByState(String openId, String state, int page, int pageSize) throws Exception;
    //通过orderID查询First的ID
    Long findFirstIdByOrderId(Long orderId)throws Exception;
    //通过orderID查询First
    First findFirstByOrderId(Long orderId)throws Exception;
    //通过ID查询First
    First findFirstById(Long id)throws Exception;
}
