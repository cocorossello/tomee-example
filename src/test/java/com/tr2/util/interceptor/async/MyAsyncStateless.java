package com.tr2.util.interceptor.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MyAsyncStateless {

    @Inject
    private MyOtherAsyncStateless other;

    @Async
    public Future<String> sayHello() {
        List<Future> tasks = new ArrayList();
        for (int i = 0; i < 100; i++) {
            tasks.add(other.sayHello());
        }
        for (Future f : tasks) {
            try {
                f.get();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new AsyncResult("hello");
    }
}
