package com.linson.android.hiandroid2.Pattern;

public class Databind_Book
{
    private String userName;
    private String userAccount;

    public Databind_Book(String userName, String userAccount) {
        this.userName = userName;
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}