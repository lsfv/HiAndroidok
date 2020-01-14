package com.linson.android.hiandroid2.DesignPattern;

import java.util.ArrayList;
import java.util.List;

import app.lslibrary.androidHelper.LSLog;

public abstract class MyState2
{
    public static void Main()
    {
        Rival HuangXiaoMing=new Rival("HuangXiaoMing");
        Rival HuangBaoQiang=new Rival("HuangBaoQiang");

        People2 jacky=new People2(1);
        People2 coco=new People2(2);


        jacky.setStatus(new NormalVote());
        coco.setStatus(new NormalVote());


        jacky.vote(HuangBaoQiang);
        jacky.vote(HuangBaoQiang);
        jacky.vote(HuangBaoQiang);
        jacky.vote(HuangBaoQiang);
        jacky.vote(HuangBaoQiang);
        jacky.vote(HuangBaoQiang);
        coco.vote(HuangXiaoMing);
        jacky.vote(HuangBaoQiang);
        jacky.vote(HuangBaoQiang);
        coco.vote(HuangBaoQiang);
        jacky.vote(HuangBaoQiang);
        coco.vote(HuangBaoQiang);
        coco.vote(HuangBaoQiang);
        coco.vote(HuangBaoQiang);
        coco.vote(HuangBaoQiang);



    }

    public interface IStatus
    {
        void vote(Rival rival,People2 obj);
    }


    public static class NormalVote implements IStatus
    {
        @Override
        public void vote(Rival rival,People2 obj)
        {
            rival.voteMe(obj.mUid);
            obj.mCount_Vote++;
            if(obj.mCount_Vote>=5 && obj.mCount_Vote<8)
            {
                obj.setStatus(new speVote());
            }
        }
    }

    public static class speVote implements IStatus
    {
        @Override
        public void vote(Rival rival,People2 obj)
        {
            LSLog.Log_INFO(obj.mUid+"you can't vote!");
            obj.mCount_Vote++;
            if(obj.mCount_Vote>=8)
            {
                obj.setStatus(new badVote());
            }
        }
    }

    public static class badVote implements IStatus
    {
        @Override
        public void vote(Rival rival,People2 obj)
        {
            LSLog.Log_INFO(obj.mUid+"login out system!");
        }
    }

    public static class People2
    {
        public int mUid;
        public int mCount_Vote;
        public IStatus mStatus=new NormalVote();


        public People2(int uid)
        {
            mUid=uid;
        }

        public void setStatus(IStatus status)
        {
            mStatus=status;
        }

        public void vote(Rival rival)
        {
            if(mStatus!=null)
            {
                mStatus.vote(rival, this);
            }
        }
    }


    public static class People
    {
        public int mUid;
        public int mCount_Vote;
        public int state;

        public People(int uid)
        {
            mUid=uid;
        }

        //代码少。但是不能直观看出状态变迁。
        public void vote2(Rival rival)
        {
            if(mCount_Vote>=5 && mCount_Vote<8)
            {
                LSLog.Log_INFO(mUid+"you can't vote!");
                mCount_Vote++;
            }
            else if(mCount_Vote>=8)
            {
                LSLog.Log_INFO(mUid+"login out system!");
            }
            else
            {
                rival.voteMe(mUid);
                mCount_Vote++;

            }
        }

        //代码量增加，但是比较能体现状态变迁，方便和状态图对照。如果状态稍微复杂点，还是这样好。方便和状态图对比进行查错。
        public void vote(Rival rival)
        {
            if(state==0)
            {
                rival.voteMe(mUid);
                mCount_Vote++;
                if(mCount_Vote>=5 && mCount_Vote<8)
                {
                    state=1;
                }
            }
            else if(state==1)
            {
                LSLog.Log_INFO(mUid+"you can't vote!");
                mCount_Vote++;
                if(mCount_Vote>=8)
                {
                    state=2;
                }
            }
            else if(state==2)
            {
                LSLog.Log_INFO(mUid+"login out system!");
            }
        }



    }


    public static class Rival
    {
        public List<Integer> mVoter=new ArrayList<>();
        public String mRivalName;

        public Rival(String name)
        {
            mRivalName=name;
        }

        public  void voteMe(int uid)
        {
            mVoter.add(uid);
            LSLog.Log_INFO(mRivalName+":"+mVoter.size()+".");
        }
    }
}