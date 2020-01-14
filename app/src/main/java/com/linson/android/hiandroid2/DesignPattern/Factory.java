package com.linson.android.hiandroid2.DesignPattern;

//基本很少用工厂，有什么是new不能解决的？
//1.参数太复杂，不想直接用new。
// 什么！！！难道不会提供一个简单的构造函数，使用默认值去调用复杂的构造函数吗？
//2.产品太多。到达上百，几千，几万个。new 一个东西。想不起来叫什么名字。
//这个倒是有需要。用工厂的静态方法就可以。没有必要到工厂方法。
//3.创建太复杂，需要先找很多信息。才能new.
//基本不可信，还是一样.用一个方法包一下就可以，就可以为什么要工厂？
//4.对象会变成一个新的对象，如果一变，所以new代码都需要修改。
//这个才是使用工厂模式的最佳场景。

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;

import java.security.MessageDigest;


public class Factory
{
    public static void Run()
    {
        //想要一本关于二战的书。但是有不同的书商可以生产，甚至以后可能会倒闭。所以我不想知道书商的信息。
        //如果书商变化。那么所有涉及到书商的代码都要修改。所以才有了工厂模式。让工厂来使用书商，以后修改只需要修改工厂类。
        //v1告诉工厂来建立。不需要知道是哪个书商提供的书。
        FactoryBooks.createHistoryBook(1, "war", 2001);
        //v2这里也是一样，反正都是把变化放到工厂里面。
        FactoryHistroy factoryHistroy=new FactoryHistroy(1, "war", 2001);
        factoryHistroy.create();
        //v3.使用的方式可以很多中。但是根本思维是一致的。所以是工厂模式。
        BookFactory bookFactory=new BookFactory();
        bookFactory.createHistoryBook(1, "war", 2001);

    }

    public static abstract class BaseBook
    {
        public int mID;
        public String mName;
        public abstract String introduceMe();
    }

    public static class ScienceBook extends BaseBook
    {
        public String mCrazy="";

        public ScienceBook(String crazy,int id,String name)
        {
            mCrazy=crazy;
            mID=id;
            mName=name;
        }

        @Override
        public String introduceMe()
        {
            return "it is Science Book:"+mName+".id:"+mID+".crazy level:"+mCrazy;
        }
    }

    public static class HistoryBook extends BaseBook
    {
        public int mYear;

        public HistoryBook(int year,int id,String name)
        {
            mYear=year;
            mID=id;
            mName=name;
        }

        @Override
        public String introduceMe()
        {
            return "it is HistoryBook.from class:HistoryBook.  Book:"+mName+".id:"+mID+".year:"+mYear;
        }
    }

    public static class HistoryBook2 extends BaseBook
    {
        public int mYear;

        public HistoryBook2(int year,int id,String name)
        {
            mYear=year;
            mID=id;
            mName=name;
        }

        @Override
        public String introduceMe()
        {
            return "it is HistoryBook.from class :HistoryBook2. Book:"+mName+".id:"+mID+".year:"+mYear;
        }
    }

    //region factory function
    public static interface FactoryB
    {
        public BaseBook create();
    }

    public static class FactoryHistroy implements FactoryB
    {
        public int mID;
        public String mName;
        public int mYear;
        public FactoryHistroy(int id,String name,int year)
        {
            mID=id;
            mName=name;
            mYear=year;
        }

        @Override
        public BaseBook create()
        {
            //return new HistoryBook(mYear, mID, mName);
            return new HistoryBook2(mYear, mID, mName);
        }
    }
    //endregion

    //region factory
    public static class BookFactory
    {
        public BaseBook createHistoryBook(int id,String name,int year)
        {
            return new HistoryBook2(year, id, name);
        }
    }
    //endregion

    //region static fun
    public static class FactoryBooks
    {
        public static BaseBook createHistoryBook(int id,String name,int year)
        {
            //BaseBook historyBook=new HistoryBook2(year, id, name);
            BaseBook historyBook=new HistoryBook(year, id, name);//这里是变化点。
            return historyBook;
        }
        //public static HistoryBook createxxxxBook(int id,String name,int year)
        //public static HistoryBook createxxxxBook(int id,String name,int year)
        //public static HistoryBook createxxxxBook(int id,String name,int year)
        //public static HistoryBook createxxxxBook(int id,String name,int year)

    }
    //endregion
}