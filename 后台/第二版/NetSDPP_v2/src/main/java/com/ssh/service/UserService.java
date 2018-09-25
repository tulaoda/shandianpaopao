package com.ssh.service;

import com.ssh.base.DomainDao;
import com.ssh.entity.User;

public interface UserService extends DomainDao<User, String> {
    User getUserByOpenId(String openId);
}
