package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class HelloBean {

    @Inject
    private HelloAsyncStateless helloAsyncStateless;

    public void doSomething() throws Exception {
        try {
            List<Future> sometasks = new ArrayList();
            for (int i = 0; i < 500; i++) {
                sometasks.add(helloAsyncStateless.sayHello());
            }
            for (Future task : sometasks) {
                try {
                    task.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new IllegalStateException(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
