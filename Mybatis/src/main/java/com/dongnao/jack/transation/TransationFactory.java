/** 
 * @Project     DN-jack-mybatis 
 * @File        TransationFactory.java 
 * @Package     com.dongnao.jack.transation 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����9:29:56 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.transation;

import com.dongnao.jack.datasource.DataSource;

/** 
 * @Description TODO 
 * @ClassName   TransationFactory 
 * @Date        2016��10��25�� ����9:29:56 
 * @Author      dongnao.jack 
 */

public interface TransationFactory {
    
    Transation createTransation(DataSource dataSource);
    
}
