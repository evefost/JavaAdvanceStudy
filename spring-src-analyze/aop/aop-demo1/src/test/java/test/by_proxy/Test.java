package test.by_proxy;

import com.xie.aop.by_proxy.Sleepable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 代理方式
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("classpath:application-by-proxy.xml");
        Sleepable me = (Sleepable) appCtx.getBean("proxy");
        me.sleep();
        System.out.println("=======");
    }
}  