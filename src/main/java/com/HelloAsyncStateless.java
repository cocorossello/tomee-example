package com;

import com.tr2.util.interceptor.async.Async;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class HelloAsyncStateless {

    @Async
    public Future<String> sayHello() {
        return new AsyncResult("hello");
    }
}
