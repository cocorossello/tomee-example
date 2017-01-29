package com.tr2.util.interceptor.async;

import javax.inject.Inject;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Descriptor;
import org.apache.openejb.testing.Descriptors;
import org.apache.openejb.testing.Module;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ApplicationComposer.class)
@Descriptors(
        @Descriptor(name = "resources.xml", path = "test-resources.xml"))
public class AsyncInterceptorIT {

    @Inject
    MyStateless stateless;

    @Module
    @Classes(cdi = true, value = {
        com.tr2.util.interceptor.async.AsyncInterceptor.class,
        MyStateless.class,
        MyAsyncStateless.class,
        MyOtherAsyncStateless.class,})
    public WebApp app() {
        return new WebApp().contextRoot("/");
    }

    @Test
    public void test1() {
        stateless.sayHello();
    }
}
