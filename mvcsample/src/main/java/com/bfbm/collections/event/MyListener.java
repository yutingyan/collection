package com.bfbm.collections.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyListener implements ApplicationListener<ApplicationEvent>{

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof MyEvent){
            System.out.println(((MyEvent) event).getText());
        }else{
            System.out.println("other envet");
        }
    }

}