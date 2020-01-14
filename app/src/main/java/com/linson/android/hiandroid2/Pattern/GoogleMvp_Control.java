package com.linson.android.hiandroid2.Pattern;


public class GoogleMvp_Control implements GoogleMvp_constract.IControl
{
    private layer_business mLayer_business=new layer_business();

    private GoogleMvp_constract.Iview mIview;

    public GoogleMvp_Control(GoogleMvp_constract.Iview iview)
    {
        mIview=iview;//
        mIview.setControl(this);//todo 这里还有不足，如果我不写，也不会报错嘛。 google的样例就是放入到这里
    }

    public String getStrLength(String str)
    {
        String info=mLayer_business.getStringInfo(str);
        return info;
    }
}