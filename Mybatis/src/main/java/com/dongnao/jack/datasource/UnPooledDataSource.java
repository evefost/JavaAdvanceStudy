/** 
 * @Project     DN-jack-mybatis 
 * @File        UnPooledDataSource.java 
 * @Package     com.dongnao.jack.datasource 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午9:41:04 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** 
 * @Description TODO 
 * @ClassName   UnPooledDataSource 
 * @Date        2016年10月27日 下午9:41:04 
 * @Author      dongnao.jack 
 */

public class UnPooledDataSource implements DataSource {
    
    private String driver;
    
    private String url;
    
    private String userName;
    
    private String password;
    
    public UnPooledDataSource(String driver, String url, String userName,
            String password) {
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.password = password;
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
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Connection getConnection() {
        
        try {
            Class.forName(driver);
            
            Connection conn = DriverManager.getConnection(url,
                    userName,
                    password);
            
            return conn;
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
}
