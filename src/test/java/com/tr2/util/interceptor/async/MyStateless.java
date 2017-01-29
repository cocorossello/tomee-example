package com.tr2.util.interceptor.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MyStateless {

    @Inject
    private MyAsyncStateless async;

    public void sayHello() {
        List<Future> tasks = new ArrayList();
        for (int i = 0; i < 200; i++) {
            tasks.add(async.sayHello());

        }
        for (Future f : tasks) {
            try {
                f.get();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

    }
}
