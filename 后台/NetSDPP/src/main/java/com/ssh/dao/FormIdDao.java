package com.ssh.dao;

import com.ssh.base.DomainDao;
import com.ssh.entity.FormId;

public interface FormIdDao extends DomainDao<FormId, Long> {
    FormId findByOpenId(String openId);
}
