/** 
 * @Project     DN-jack-mybatis 
 * @File        UnPooledDataSourceFactory.java 
 * @Package     com.dongnao.jack.datasource 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午9:36:40 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.datasource;

/** 
 * @Description 创建数据源的工厂类 
 * @ClassName   UnPooledDataSourceFactory 
 * @Date        2016年10月27日 下午9:36:40 
 * @Author      dongnao.jack 
 */

public class UnPooledDataSourceFactory implements DataSourceFactory {
    
    private String driver;
    
    private String url;
    
    private String username;
    
    private String password;
    
    public UnPooledDataSourceFactory() {
        
    }
    
    public UnPooledDataSourceFactory(String driver, String url,
            String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public DataSource getDataSource() {
        return new UnPooledDataSource(driver, url, username, password);
    }
    
    public String getDriver() {
        return driver;
    }
    
    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
