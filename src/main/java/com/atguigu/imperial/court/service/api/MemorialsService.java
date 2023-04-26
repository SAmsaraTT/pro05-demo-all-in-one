package com.atguigu.imperial.court.service.api;

import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/26 14:42
 * @Version 1.0
 */
public interface MemorialsService {
    List<Memorials> getAllMemorialsDigest();

    Memorials getAllMemorialsDetailById(String id);

    void updateMemorialsStatusToRead(String id);

    void updateMemorialsFeedBack(String id, String feedbackContent);
}
