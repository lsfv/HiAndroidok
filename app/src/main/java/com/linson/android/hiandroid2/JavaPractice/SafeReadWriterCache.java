package com.linson.android.hiandroid2.JavaPractice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SafeReadWriterCache<key,value>
{
    private Map<key,value> mMap=new HashMap<>();
    private ReentrantReadWriteLock mReentrantReadWriteLock=new ReentrantReadWriteLock();

    public value get(key k)
    {
        value res=null;
        mReentrantReadWriteLock.readLock().lock();
        try
        {
            res=mMap.get(k);
        }
        finally
        {
            mReentrantReadWriteLock.readLock().unlock();
        }
        return res;
    }

    public void set(key kk,value vv)
    {
        mReentrantReadWriteLock.writeLock().lock();
        try
        {
            mMap.put(kk, vv);
        }
        finally
        {
            mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    public void clear()
    {
        mReentrantReadWriteLock.writeLock().lock();
        try
        {
            mMap.clear();
        }
        finally
        {
            mReentrantReadWriteLock.writeLock().unlock();
        }
    }
}