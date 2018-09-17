package com.ssh.dao;

import com.ssh.base.DomainDao;
import com.ssh.entity.First;

import java.util.List;


public interface FirstDao extends DomainDao<First, Long> {
    //通过orderID修改订单状态
    void updateFirstStateByOrderId(Long orderId, String state);

    //按订单状态分页查询订单
    List<First> orderByState(String openId, String state, int page, int pageSize);

    //按状态查询所有订单
    List<First> orderAllByState(String state, int page, int pageSize);


    //通过orderID查询First的ID
    Long findFirstIdByOrderId(Long orderId);

    //通过orderID查询First
    First findFirstByOrderId(Long orderId);

    //通过ID查询First
    First findFirstById(Long id);

    //通过OrderID查询price
    Double getPrice(Long orderId);
}
