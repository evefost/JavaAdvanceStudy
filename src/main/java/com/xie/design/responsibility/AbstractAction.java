package com.xie.design.responsibility;

/**
 * Created by xieyang on 17/1/23.
 */
public abstract class AbstractAction implements  Action{


    protected  Action nextAction;


    public void todo(String type) {
        System.out.println("我是你父新,我来处理");
    }

    public Action getNextAction() {
        return nextAction;
    }

    public void setNextAction(Action nextAction) {
        this.nextAction = nextAction;
    }
}
