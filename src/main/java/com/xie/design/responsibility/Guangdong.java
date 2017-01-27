package com.xie.design.responsibility;

/**
 * Created by xieyang on 17/1/23.
 */
public class Guangdong extends AbstractAction {

    public  static  String TYPE="广东";

    @Override
    public void todo(String type) {
        if(TYPE.equals(type)){
            System.out.println("我是广东,只处理广东的业务");
        }else {
          if(nextAction != null){
              nextAction.todo(type);
          }else {
              super.todo(type);
          }
        }
    }
}
