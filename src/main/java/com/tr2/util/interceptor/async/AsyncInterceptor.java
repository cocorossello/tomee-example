package com.tr2.util.interceptor.async;

import java.io.Serializable;
import java.util.concurrent.Future;
import javax.annotation.Priority;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import static javax.interceptor.Interceptor.Priority.APPLICATION;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;

@Async
@Interceptor
@Priority(APPLICATION)
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
