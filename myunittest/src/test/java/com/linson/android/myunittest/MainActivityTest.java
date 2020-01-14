package com.linson.android.myunittest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


//没有单元测试框架，我必须在项目中自己建立测试类，所以就必须启动项目
//而as的单元测试框架，可以方便的测试，
//1，提供快捷按钮方式，启动单方法，单类，整目录。
//2.直接可以到ide窗口查看测试结果，非常方便。
//3,提供测试日志和测试专用函数，无需自己编写。
public class MainActivityTest
{
    private MainActivity.myTestClass mtestClass;
    public MainActivityTest()
    {
        mtestClass=new MainActivity.myTestClass();
    }

    @Test
    public void add()
    {
        Integer res= MainActivity.add(null, 4);
        assertEquals(res, null);

        Integer res2= MainActivity.add(3, 4);
        assertEquals(res2.intValue(), 7);

        Integer res3= MainActivity.add(3, -4);
        assertEquals(res3.intValue(), -1);
    }


    @Test
    public void add2()
    {
        assertEquals(mtestClass.add(3, 4), 7);

    }

    @Test
    public void sub2()
    {
        assertEquals(mtestClass.sub(3, 4), -1);
    }

}