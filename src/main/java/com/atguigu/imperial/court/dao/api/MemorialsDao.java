package com.atguigu.imperial.court.dao.api;

import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/20 20:38
 * @Version 1.0
 */
public interface MemorialsDao {
    List<Memorials> selectAllMemorialsDigest();

    Memorials selectMemorialsById(String id);

    void updateMemorialsStatusToRead(String id);

    void updateMemorialsFeedBack(String id, String feedbackContent);
}
