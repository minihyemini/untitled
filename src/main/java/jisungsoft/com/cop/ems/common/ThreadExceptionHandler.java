package jisungsoft.com.cop.ems.common;

import org.springframework.stereotype.Service;

@Service
public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String handlerName;

    public ThreadExceptionHandler(){
        handlerName = "This";
    }

    public ThreadExceptionHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        System.out.println(handlerName + " caught Exception in Thread - " + thread.getName() + " => " + e);
    }
}
