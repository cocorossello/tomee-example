package com;

import com.tr2.test.MyStateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class HelloBean {

    @Inject
    MyStateless stateless;

    public void doSomething() throws Exception {
        stateless.sayHello();
    }
}
