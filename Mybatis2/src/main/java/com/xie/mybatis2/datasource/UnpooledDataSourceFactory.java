package com.xie.mybatis2.datasource;

/**
 * Created by xieyang on 17/1/26.
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    private String driver;

    private String url;

    private String username;

    private String password;

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

    public UnpooledDataSourceFactory() {

    }

    public UnpooledDataSourceFactory(String driver, String url,
                                     String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public DataSource getDataSource() {
        return new UnPooledDataSource(driver, url, username, password);
    }
}
