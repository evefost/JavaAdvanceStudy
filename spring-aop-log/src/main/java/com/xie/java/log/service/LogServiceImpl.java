package com.xie.java.log.service;


import com.xie.java.log.anotation.OperateAno;
import com.xie.java.log.vo.Param;
import com.xie.java.log.vo.Param2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl implements LogService {

    private Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Override
    @OperateAno(operate = "登录1",accountId = "#param.accountId")
    public void addLog(Param param) {
        logger.info("登录1 业务操作。。。。");
    }

    @Override
    @OperateAno(operate = "登录2",accountId = "#param.accountId")
    public void addLog(Param2 param, Long id) {
        logger.info("登录2 业务操作。。。。");
    }
}
