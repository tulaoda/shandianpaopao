package com.ssh.service.impl;

import com.ssh.dao.BannerDao;
import com.ssh.entity.Banner;
import com.ssh.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    public Banner load(Long id) {
        return null;
    }

    public Banner get(Long id) {
        return null;
    }

    public List<Banner> findAll() {
        return bannerDao.findAll();
    }

    public void persist(Banner entity) {

    }

    public Long save(Banner entity) {
        return bannerDao.save(entity);
    }

    public void saveOrUpdate(Banner entity) {

    }

    public void delete(Long id) {

    }

    public void flush() {

    }
}
