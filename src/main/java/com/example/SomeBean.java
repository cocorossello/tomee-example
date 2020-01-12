package com.example;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class SomeBean {

    public void sayHi() {
        System.out.println("Hi");
    }
}
