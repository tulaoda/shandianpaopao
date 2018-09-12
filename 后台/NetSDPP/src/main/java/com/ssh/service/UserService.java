package com.ssh.service;

import com.ssh.base.DomainDao;
import com.ssh.entity.Banner;
import com.ssh.entity.User;

public interface UserService extends DomainDao<User, Long> {
    User getUserByOpenId(String openId);
}
