package com.linson.LSLibrary.AndroidHelper;

import android.support.v7.widget.RecyclerView;

public abstract class LSUI
{
    public enum Scroll_Type
    {
        contentIsLeft,
        contentIsRight,
        contentIsTop,
        contentIsBottom,
        free,
        fix,
    }

    public static Scroll_Type checkHScrollType(RecyclerView recyclerView)
    {
        final int hoffset = recyclerView.computeHorizontalScrollOffset();
        final int hMaxoffset = recyclerView.computeHorizontalScrollRange() - recyclerView.computeHorizontalScrollExtent();


        if (hMaxoffset == 0)
        {
            return Scroll_Type.fix;
        }
        if(hoffset==0)
        {
            return Scroll_Type.contentIsLeft;
        }
        else if(hoffset>=hMaxoffset)
        {
            return Scroll_Type.contentIsRight;
        }

        return Scroll_Type.free;
    }



    public static Scroll_Type checkVScrollType(RecyclerView recyclerView)
    {
        final int voffset = recyclerView.computeVerticalScrollOffset();
        final int vMaxoffset = recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent();



        if (vMaxoffset == 0)
        {
            return Scroll_Type.fix;
        }

        if(voffset==0)
        {
            return Scroll_Type.contentIsTop;
        }
        else if(voffset==vMaxoffset)
        {
            return Scroll_Type.contentIsBottom;
        }



        return Scroll_Type.free;
    }
}
