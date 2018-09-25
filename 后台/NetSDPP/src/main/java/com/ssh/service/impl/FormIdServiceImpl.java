package com.ssh.service.impl;

import com.ssh.dao.FormIdDao;
import com.ssh.entity.FormId;
import com.ssh.service.FormIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FormIdServiceImpl implements FormIdService {
    @Autowired
    private FormIdDao formIdDao;

    public FormId load(Long id) {
        return null;
    }

    public FormId get(Long id) {
        return formIdDao.get(id);
    }

    public List<FormId> findAll() {
        return null;
    }

    public void persist(FormId entity) {

    }

    public Long save(FormId entity) {
        return null;
    }

    public void saveOrUpdate(FormId entity) {
        formIdDao.saveOrUpdate(entity);
    }

    public void delete(Long id) {

    }

    public void flush() {

    }



    public FormId findByOpenId(String openId) {
        return null;
    }
}
