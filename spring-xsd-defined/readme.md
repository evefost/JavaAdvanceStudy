##一、自定义spring标签

1,定义标签文件:spring-test.xsd,
    设定命名空间：targetNamespace="http://www.lexueba.com/schema/user"
2.创建schemas文件,指定命名空间uri指向xsd文件：spring.schemas
    http\://www.lexueba.com/schema/user.xsd=META-INF/spring-test.xsd
3.spring xml 通过命名空间引用xsd 定义的签标

4.指定命名空间处理器：spring.handlers
    http\://www.lexueba.com/schema/user=com.xie.java.asm.demo1.xsd.defined.demo1.MyNamespaceHandler
    
##二、spring标签解释过程

