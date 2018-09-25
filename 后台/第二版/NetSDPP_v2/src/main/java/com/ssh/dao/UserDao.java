package com.ssh.dao;

import com.ssh.base.DomainDao;
import com.ssh.entity.User;

public interface UserDao extends DomainDao<User,String> {
    User getUserByOpenId(String openId);
}
