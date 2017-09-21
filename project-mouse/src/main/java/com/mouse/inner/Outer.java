package com.mouse.inner;

/**
 * Created by huayadlly on 2017/6/16.
 */
public class Outer {

    private String name;
    private Integer age;

    public class Inner {
        private String inner_name;
        public Integer inner_age;

        public void innerPrint() {
            System.out.println("inner_method is printing inner_name:" + inner_name + ",inner_age:" + inner_age);
        }
    }

    public void outerPrint() {
        System.out.println("outer_method is printting outer_name:" + name + ",age:" + age);
    }
}
