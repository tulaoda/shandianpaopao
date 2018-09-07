package com.ssh.dao;

import com.ssh.base.DomainDao;
import com.ssh.entity.First;

import java.util.List;

public interface FirstDao extends DomainDao<First, Long> {

    List<First> orderByState(String openId, String state);

}
