package com.xie.mybatis2.dao;


import com.dongnao.jack.testBean.TestBean;

import java.util.List;


public interface TestMapper {

    List<TestBean> selectList(Integer id);

}
