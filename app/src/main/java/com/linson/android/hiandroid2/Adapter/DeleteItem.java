package com.linson.android.hiandroid2.Adapter;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.linson.android.hiandroid2.R;
import java.util.LinkedList;
import java.util.List;

import app.lslibrary.androidHelper.LSLog;


public class DeleteItem extends RecyclerView.Adapter<DeleteItem.MyHolderView>
{
    private List<String> mdata=new LinkedList<>();
    private float prex=-1;
    private int maxWidth=240;
    private boolean isHiden=true;

    public DeleteItem()
    {
        mdata.add("HI!!!!!!!!!!!!!!!!!!!!");
        mdata.add("Hello!!!!!!!!!!!!!!!!!!!!!1");
    }

    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_delete, viewGroup, false);

        return new MyHolderView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyHolderView myHolderView, int i)
    {
        myHolderView.mTvItem.setText(mdata.get(i));
        myHolderView.mButton21.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LSLog.Log_INFO("click");
            }
        });

        myHolderView.mView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    prex=event.getX();
                }
                else if(event.getAction()==MotionEvent.ACTION_MOVE)
                {
                    ConstraintLayout.LayoutParams lp=(ConstraintLayout.LayoutParams)myHolderView.mTvItem.getLayoutParams();

                    float currentX=event.getX();
                    float movex=currentX-prex;
                    int lastMarginend=(int)(lp.getMarginEnd()-movex);
                    lastMarginend=lastMarginend>maxWidth?maxWidth:lastMarginend;
                    lastMarginend=lastMarginend<0?0:lastMarginend;
                    lp.setMargins(0, 0, lastMarginend, 0);
                    myHolderView.mTvItem.setLayoutParams(lp);
                    prex=currentX;
                }
                else if(event.getAction()==MotionEvent.ACTION_CANCEL ||event.getAction()==MotionEvent.ACTION_UP )
                {
                    final ConstraintLayout.LayoutParams lp=(ConstraintLayout.LayoutParams)myHolderView.mTvItem.getLayoutParams();
                    boolean wantToShow=true;
                    int currentMargin=lp.getMarginEnd();
                    if(isHiden)
                    {
                        wantToShow=(float)currentMargin/maxWidth>=0.5?true:false;//键盘判断，拖动超过一半就显示.
                    }
                    else
                    {
                        wantToShow=false;//显示状态，除非触碰按钮，否则无条件关闭按钮。
                    }

                    int endMargin;
                    if(wantToShow)
                    {
                        endMargin=maxWidth;
                        isHiden=false;
                    }
                    else
                    {
                        endMargin=0;
                        isHiden=true;
                    }
                    //play animator
                    ValueAnimator animator=ValueAnimator.ofInt(currentMargin,endMargin);
                    animator.setDuration(300);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                    {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation)
                        {
                            int changingValue=(int)animation.getAnimatedValue();
                            lp.setMargins(0, 0, changingValue, 0);
                            myHolderView.mTvItem.setLayoutParams(lp);
                        }
                    });
                    animator.start();
                    //end play animator
                }
                return true;
            }
        });
    }


    @Override
    public int getItemCount()
    {
        return mdata.size();
    }


    public static class MyHolderView extends RecyclerView.ViewHolder
    {
        private TextView mTvItem;
        private Button mButton21;
        private View mView;

        public MyHolderView(@NonNull View itemView)
        {
            super(itemView);
            mTvItem = (TextView) itemView.findViewById(R.id.tv_item);
            mButton21 = (Button) itemView.findViewById(R.id.button21);
            mView=itemView;
        }
    }
}