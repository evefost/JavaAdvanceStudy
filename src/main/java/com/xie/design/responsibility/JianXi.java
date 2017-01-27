package com.xie.design.responsibility;

/**
 * Created by xieyang on 17/1/23.
 */
public class JianXi extends AbstractAction {

    public  static  String TYPE="江西";

    @Override
    public void todo(String type) {
        if(TYPE.equals(type)){
            System.out.println("我是江西,只处理江西的业务");
        }else {
          if(nextAction != null){
              nextAction.todo(type);
          }else {
              super.todo(type);
          }
        }
    }
}
