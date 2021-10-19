package org.web.base.example.springcloud;

public class A {
    private static final int flag=1;
    public static void main(String[] args) {
        int a=0;
        int c=0;
        do{
            --c;
            a=a-1;
        }while(a>0);
        System.out.println(c);
    }
}
