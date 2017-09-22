package test.com.xie.java.spring.el;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyang on 17/9/22.
 */
public class SpringELTest {

    // 1. 构建解析器
    //    ExpressionParser parser = new SpelExpressionParser();
    // 2. 解析表达式
    //    Expression exp = parser.parseExpression(SpEl);
    // 3. 获取结果
    //    exp.getValue();
    ExpressionParser parser = new SpelExpressionParser();

    /**
     * 1.文本表达式
     * 文本表达式支持: 字符串(需要用单引号声明)、日期、数字、布尔类型及null,
     * 对数字支持负数、指数及小数, 默认情况下实数使用Double.parseDouble()进行表达式类型转换.
     */
    @Test
    public void test1() {


        String value = parser.parseExpression("'hello'").getValue(String.class);// hello , 注意单引号

        Long value1 = parser.parseExpression("1.024E+3").getValue(Long.class);// 1024  , 指数形式

        Integer value2 = parser.parseExpression("0xFFFF").getValue(Integer.class);// 65535 , 十六进制

        Boolean aTrue = parser.parseExpression("true").getValue(Boolean.class);// true

        Object aNull = parser.parseExpression("null").getValue();

        System.out.println(value);
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(aTrue);
        System.out.println(value);
        System.out.println(aNull);

    }


    /**
     * 2.变量
     * 变量可以通过StandardEvaluationContext的setVariable方法设置到上下文中, 表达式中可以通过#变量名使用变量;
     * 另外, 还可以直接使用构造方法创建对象.
     */

    @Test
    public void test2() {

        // 定义变量
        String name = "Tom";
        EvaluationContext context = new StandardEvaluationContext();  // 表达式的上下文,
        context.setVariable("myName", name);                        // 为了让表达式可以访问该对象, 先把对象放到上下文中
        ExpressionParser parser = new SpelExpressionParser();
        // 访问变量
        String value = parser.parseExpression("#myName").getValue(context, String.class);// Tom , 使用变量

        // 直接使用构造方法创建对象
        String value1 = parser.parseExpression("new String('aaa')").getValue(String.class);// aaa

        System.out.println(value);
        System.out.println(value1);
    }

    /**
     * 3.属性和方法调用
     * 属性可直接使用属性名,属性名首字母大小写均可(只有首字母可不区分大小写);
     * 数组、列表可直接通过下表形式(list[index])访问;
     * map可以直接把key当成索引来访问(map[key]);
     * 方法可以直接访问;
     */
    @Test
    public void test3() {
        Person person = new Person("Tom", 18); // 一个普通的POJO
        List<String> list = Lists.newArrayList("a", "b");
        Map<String, String> map = Maps.newHashMap();
        map.put("A", "1");
        map.put("B", "2");
        EvaluationContext context = new StandardEvaluationContext();  // 表达式的上下文,
        context.setVariable("person", person);                        // 为了让表达式可以访问该对象, 先把对象放到上下文中
        context.setVariable("map", map);
        context.setVariable("list", list);
        ExpressionParser parser = new SpelExpressionParser();
        // 属性
        String value = parser.parseExpression("#person.name").getValue(context, String.class);// Tom , 属性访问

        // Tom , 属性访问, 但是首字母大写了
        String value1 = parser.parseExpression("#person.Name").getValue(context, String.class);

        // 列表
        String value2 = parser.parseExpression("#list[0]").getValue(context, String.class);// a , 下标

        // map
        String value3 = parser.parseExpression("#map[A]").getValue(context, String.class);// 1 , key
        // 方法
        Integer value4 = parser.parseExpression("#person.getAge()").getValue(context, Integer.class);// 18 , 方法访问


        System.out.println(value);
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
        System.out.println(value4);

    }
}
