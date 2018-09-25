package com.ssh.service;

import com.ssh.base.DomainDao;
import com.ssh.entity.First;
import com.ssh.entity.FormId;

public interface FormIdService extends DomainDao<FormId, Long>  {
    FormId findByOpenId(String openId);
}
