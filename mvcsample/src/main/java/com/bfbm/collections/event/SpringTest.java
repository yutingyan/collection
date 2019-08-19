package com.bfbm.collections.event;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.*;

public class SpringTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyEvent event = new MyEvent("hello World");
        event.setText("hello");

        context.publishEvent(event);
    }
}