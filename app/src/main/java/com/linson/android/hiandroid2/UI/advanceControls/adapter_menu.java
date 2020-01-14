package com.linson.android.hiandroid2.UI.advanceControls;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.hiandroid2.R;

import java.util.List;

public class adapter_menu extends RecyclerView.Adapter<adapter_menu.myviewHolder>
{
    public List<String> mdata;
    public adapter_menu(List<String> data)
    {
        mdata=data;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemview=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_singlebutton, viewGroup, false);
        return new myviewHolder(itemview);
    }


    @Override
    public void onBindViewHolder(@NonNull final myviewHolder myviewHolder, int i)
    {
        String itemMenu=mdata.get(i);
        myviewHolder.mButton.setText(itemMenu);

    }


    @Override
    public int getItemCount()
    {
        return mdata.size();
    }


    public static class myviewHolder extends RecyclerView.ViewHolder
    {
        private Button mButton;
        private View mView;
        public myviewHolder(@NonNull View itemView)
        {
            super(itemView);
            mButton=(Button) itemView.findViewById(R.id.btn_link);
            mView=itemView;
        }
    }

}