package org.web.base.example.springcloud;

public class NotifyTest {

    public static void main(String[] args) {
        System.out.println("begin");
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
        NotifyThread test = new NotifyThread();
        test.start();
        test.synFunction2();
    }
}
