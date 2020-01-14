package com.linson.android.hiandroid2.JavaPractice;

public  class MyBook
{
    private int price;
    public int id;
    public String name;

    public MyBook(){}

    public MyBook(int p,int ida,String namea)
    {
        price=p;
        id=ida;
        name=namea;
    }
    public String getInfo()
    {
        return "price:"+price+",id:"+id+",name:"+name;
    }
}