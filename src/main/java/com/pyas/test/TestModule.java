package com.pyas.test;

public class TestModule {


    public static final String BINDKEY;

    static {
        BINDKEY ="11";
    }

    public static void main(String[] args) {
        String bindkey = TestModule.BINDKEY;
        System.out.println(bindkey);
    }

}
