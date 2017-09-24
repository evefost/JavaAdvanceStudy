package com.xie.java.log.aop;

import com.xie.java.log.anotation.OperateAno;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(op)")
    public Object doAround(ProceedingJoinPoint pjp, OperateAno op) throws Throwable {
        try {
            String logName = op.operate();
            String accountId = getKey(op.accountId(), pjp);
            logger.info("操作:{}",logName);
            logger.info("操作帐号:{}",accountId);
        }catch (Exception e){
            logger.warn("操作日志异常 ,{}", e);
        }
        Object ret = pjp.proceed();
        return ret;

    }

    //使用spring el表达式来获取函数的入参
    private String getKey(String key, ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        Signature signature = pjp.getSignature();
        MethodSignature ms = (MethodSignature) signature;
        Method method = ms.getMethod();
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        return SpelParser.getKey(key, parameterNames, args);
    }
}
