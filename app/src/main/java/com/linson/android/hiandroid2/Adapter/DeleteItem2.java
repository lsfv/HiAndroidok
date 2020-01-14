package com.linson.android.hiandroid2.Adapter;

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

public class DeleteItem2 extends RecyclerView.Adapter<DeleteItem2.MyHolderView>
{
    private List<String> mdata = new LinkedList<>();
    private float prex = -1;
    private int minWidht = 1;
    private int maxWidth = 240;

    public DeleteItem2()
    {
        mdata.add("HI!!!!!!!!!!!!!!!!!!!!2");
        mdata.add("Hello!!!!!!!!!!!!!!!!!!!!!2");
    }

    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_delete2, viewGroup, false);

        return new MyHolderView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyHolderView myHolderView, int i)
    {
        myHolderView.mView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                ConstraintLayout.LayoutParams params= (ConstraintLayout.LayoutParams) myHolderView.mTextView20.getLayoutParams();
                params.setMargins(0, 0, params.getMarginEnd()+10, 0);
                myHolderView.mTextView20.setLayoutParams(params);


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
        private ConstraintLayout mItemDelete;
        private TextView mTextView20;
        private Button mButton22;
        private View mView;

        public MyHolderView(@NonNull View itemView)
        {
            super(itemView);
            mView = itemView;

            mItemDelete = (ConstraintLayout) itemView.findViewById(R.id.item_delete);
            mTextView20 = (TextView) itemView.findViewById(R.id.textView20);
            mButton22 = (Button) itemView.findViewById(R.id.button22);
        }
    }

}