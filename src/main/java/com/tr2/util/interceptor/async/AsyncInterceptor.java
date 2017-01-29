package com.tr2.util.interceptor.async;

import java.io.Serializable;
import java.util.concurrent.Future;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;

@Async
@Interceptor
public class AsyncInterceptor implements Serializable {

    private ManagedExecutorService executor;

    @AroundInvoke
    public Object submitAsync(InvocationContext ctx) throws Exception {
        if (executor == null) {
            executor = (ManagedExecutorService) new InitialContext().lookup("openejb:Resource/TravelcAsynchronousPool");
        }
        Future resultFuture = new FutureDelegator(executor.submit(() -> {
            return ctx.proceed();
        }));
        if (ctx.getMethod().getReturnType() == Void.TYPE) {
            return null;
        }

        return resultFuture;
    }
}
