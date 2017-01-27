package com.xie.design.responsibility;

/**
 * Created by xieyang on 17/1/23.
 */
public class HuNan extends AbstractAction {

    public  static  String TYPE="湖南";

    @Override
    public void todo(String type) {
        if(TYPE.equals(type)){
            System.out.println("我是湖南,只处理湖南的业务");
        }else {
          if(nextAction != null){
              nextAction.todo(type);
          }else {
              super.todo(type);
          }
        }
    }
}
