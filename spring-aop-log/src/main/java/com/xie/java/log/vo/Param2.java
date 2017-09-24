package com.xie.java.log.vo;

/**
 * Created by Administrator on 2017/9/24.
 */
public class Param2 {

//    @AccountId
    private Long accountId;

    private String name;
    private String operateContent;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }
}
