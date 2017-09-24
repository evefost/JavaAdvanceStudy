import com.xie.java.log.service.LogService;
import com.xie.java.log.vo.Param;
import com.xie.java.log.vo.Param2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LogTest {

    @Resource
    private LogService logService;
    @Test
    public void testCache() {
        Param param = new Param();
        param.setAccountId(12345l);
        param.setName("张三");
        logService.addLog(param);

        Param2 param2 = new Param2();
        param2.setAccountId(6789l);
        param2.setName("李四");
        //logService.addLog(param2,666l);

    }
}
