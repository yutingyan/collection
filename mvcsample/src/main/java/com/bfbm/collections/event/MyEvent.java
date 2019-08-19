package com.bfbm.collections.event;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent{

    private String text;

    public MyEvent(Object source) {
        super(source);
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

}