#spring标签

##一、自定义spring标签

1. 创建定义标签文件:spring-test.xsd,
    设定命名空间：targetNamespace="http://www.lexueba.com/schema/user" 
2. 创建schemas文件,指定命名空间uri指向xsd文件：spring.schemas
    http\://www.lexueba.com/schema/user.xsd=META-INF/spring-test.xsd
3. spring xml 通过命名空间引用xsd 定义的签标
4. 指定命名空间处理器：spring.handlers
    http\://www.lexueba.com/schema/user=com.xie.java.asm.demo1.xsd.defined.demo1.MyNamespaceHandler
    
##二、spring标签解释过程

1.spring启动

AbstractApplicationContext

    public void refresh() throws BeansException, IllegalStateException {
            Object var1 = this.startupShutdownMonitor;
            synchronized(this.startupShutdownMonitor) {
                this.prepareRefresh();
                //加载beanfactory,读取资源文件
                ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory();
                this.prepareBeanFactory(beanFactory);
                try {
                    this.postProcessBeanFactory(beanFactory);
          
 
2.读取xml资源文件    
  
XmlBeanDefinitionReader

    public int registerBeanDefinitions(Document doc, Resource resource) throws BeanDefinitionStoreException {
        BeanDefinitionDocumentReader documentReader = this.createBeanDefinitionDocumentReader();
        int countBefore = this.getRegistry().getBeanDefinitionCount();
        documentReader.registerBeanDefinitions(doc, this.createReaderContext(resource));
        return this.getRegistry().getBeanDefinitionCount() - countBefore;
    }
3.选择解释spring 自带或 自定义标签 

DefaultBeanDefinitionDocumentReader

    protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
        if(delegate.isDefaultNamespace(root)) {
            NodeList nl = root.getChildNodes();

            for(int i = 0; i < nl.getLength(); ++i) {
                Node node = nl.item(i);
                if(node instanceof Element) {
                    Element ele = (Element)node;
                    if(delegate.isDefaultNamespace(ele)) {
                        //解释spring 自带标签
                        this.parseDefaultElement(ele, delegate);
                    } else {
                        //解释自定义标签
                        delegate.parseCustomElement(ele);
                    }
                }
            }
        } else {
            delegate.parseCustomElement(root);
        }

    }
 
BeanDefinitionParserDelegate
  
      public BeanDefinition parseCustomElement(Element ele, BeanDefinition containingBd) {
          String namespaceUri = this.getNamespaceURI(ele);
          //根据命名空间，获取对应handler
          NamespaceHandler handler = this.readerContext.getNamespaceHandlerResolver().resolve(namespaceUri);
          if(handler == null) {
              this.error("Unable to locate Spring NamespaceHandler for XML schema namespace [" + namespaceUri + "]", ele);
              return null;
          } else {
               //执自parse方法(自定义标签)
              return handler.parse(ele, new ParserContext(this.readerContext, this, containingBd));
          }
      }
      
      
DefaultNamespaceHandlerResolver