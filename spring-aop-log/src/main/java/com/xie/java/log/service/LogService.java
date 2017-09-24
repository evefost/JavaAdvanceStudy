package com.xie.java.log.service;


import com.xie.java.log.vo.Param;
import com.xie.java.log.vo.Param2;

/**
 * Created by Administrator on 2017/9/24.
 */
public interface LogService {

    void addLog(Param param);

    void addLog(Param2 param, Long id);
}
