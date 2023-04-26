package com.atguigu.imperial.court.service.impl;

import com.atguigu.imperial.court.dao.api.MemorialsDao;
import com.atguigu.imperial.court.dao.impl.MemorialsDaoImpl;
import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.service.api.MemorialsService;

import java.util.List;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/26 14:42
 * @Version 1.0
 */
public class MemorialsServiceImpl implements MemorialsService {
    private MemorialsDao memorialsDao = new MemorialsDaoImpl();

    @Override
    public List<Memorials> getAllMemorialsDigest() {

        return memorialsDao.selectAllMemorialsDigest();
    }

    @Override
    public Memorials getAllMemorialsDetailById(String id) {
        return memorialsDao.selectMemorialsById(id);
    }

    @Override
    public void updateMemorialsStatusToRead(String id) {
        memorialsDao.updateMemorialsStatusToRead(id);
    }

    @Override
    public void updateMemorialsFeedBack(String id, String feedbackContent) {
        memorialsDao.updateMemorialsFeedBack(id, feedbackContent);
    }
}
