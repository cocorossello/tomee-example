package com.tr2.util.interceptor.async;

import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Stateless;

@Stateless
public class MyOtherAsyncStateless {

    @Async
    public Future<String> sayHello() {
        return new AsyncResult("hello");
    }
}
