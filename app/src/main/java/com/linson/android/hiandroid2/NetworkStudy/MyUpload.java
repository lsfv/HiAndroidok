package com.linson.android.hiandroid2.NetworkStudy;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.linson.LSLibrary.Network.LSOKHttp;
import com.linson.android.hiandroid2.R;
import java.io.File;
import java.util.Map;
import app.lslibrary.androidHelper.LSContentResolver;
import app.lslibrary.androidHelper.LSLog;


//1.是否有临时数据。2.有，数据展示上去，并提供上传和返回和放弃按钮。2.2没有 提供上传，返回按钮
//a.双传：双传需要传送的。每次成功一个，就修改一个信息。
//b.放弃：清除记录，并返回。
//c.返回：保存数据（如果是全部成功，那么清除数据），并返回。
//0.init 1.temp.
//1.界面和数据保持同步。2.
public class MyUpload extends AppCompatActivity implements View.OnClickListener
{
    private static final String SHARE_FILENAME="uploadFile";
    private static final String SHARE_FILE1="uploadFile1";
    private static final String SHARE_FILE2="uploadFile2";
    private static final String SHARE_FILE3="uploadFile3";
    private static final int CODE_CONTENT_PERMISSION1=1;
    private static final int CODE_CONTENT_PERMISSION2=2;
    private static final int CODE_CONTENT_PERMISSION3=3;
    private static final int CODE_STARTACTIVITY1=1;
    private static final int CODE_STARTACTIVITY2=2;
    private static final int CODE_STARTACTIVITY3=3;


    private IState mState;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_upload);

        mMyControls=new MyControls();//cut it into 'onCreate'


        if(isInit())
        {
            mState=new InitState();
        }
        else
        {
            mState=new StageState();
        }

        //状态模式好爽啊.
        mState.UIControl();


        mMyControls.mImageView24.setOnClickListener(this);
        mMyControls.mImageView25.setOnClickListener(this);
        mMyControls.mImageView26.setOnClickListener(this);

        mMyControls.mBtnUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==mMyControls.mImageView24.getId())
        {
            LSContentResolver.checkPermission(MyUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE, CODE_CONTENT_PERMISSION1, new GetContent(CODE_CONTENT_PERMISSION1));
        }
        else if(v.getId()==mMyControls.mImageView25.getId())
        {
            LSContentResolver.checkPermission(MyUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE, CODE_CONTENT_PERMISSION2, new GetContent(CODE_CONTENT_PERMISSION2));
        }
        else if(v.getId()==mMyControls.mImageView26.getId())
        {
            LSContentResolver.checkPermission(MyUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE, CODE_CONTENT_PERMISSION3, new GetContent(CODE_CONTENT_PERMISSION3));
        }
        else if(v.getId()==mMyControls.mBtnUpload.getId())
        {
            mState.uploadPic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode==CODE_CONTENT_PERMISSION1)
        {
            LSContentResolver.progressCheck(this, requestCode, grantResults, CODE_CONTENT_PERMISSION1, new GetContent(CODE_CONTENT_PERMISSION1));
        }
        else if(requestCode==CODE_CONTENT_PERMISSION2)
        {
            LSContentResolver.progressCheck(this, requestCode, grantResults, CODE_CONTENT_PERMISSION2, new GetContent(CODE_CONTENT_PERMISSION2));
        }
        else if(requestCode==CODE_CONTENT_PERMISSION3)
        {
            LSContentResolver.progressCheck(this, requestCode, grantResults, CODE_CONTENT_PERMISSION3, new GetContent(CODE_CONTENT_PERMISSION3));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==CODE_CONTENT_PERMISSION2 ||requestCode==CODE_CONTENT_PERMISSION1 ||requestCode==CODE_CONTENT_PERMISSION3)
        {

            SharedPreferences share= getSharedPreferences(SHARE_FILENAME, MODE_PRIVATE);
            SharedPreferences.Editor editor=share.edit();
            Uri pic= data.getData();
            String picPath=LSContentResolver.getBitmapPaht(this, pic);

            if(requestCode==CODE_CONTENT_PERMISSION1)
            {
                ImageView view=mMyControls.mImageView24;
                editor.putString(SHARE_FILE1, picPath);
                view.setImageBitmap(BitmapFactory.decodeFile(picPath));
                editor.commit();
                LSLog.Log_INFO(String.format("%s,path:%s",SHARE_FILE1,picPath));
            }
            else if(requestCode==CODE_CONTENT_PERMISSION2)
            {
                ImageView view=mMyControls.mImageView25;
                editor.putString(SHARE_FILE2, picPath);
                view.setImageBitmap(BitmapFactory.decodeFile(picPath));
                editor.commit();
                LSLog.Log_INFO(String.format("%s,path:%s",SHARE_FILE1,picPath));
            }
            else
            {
                ImageView view=mMyControls.mImageView26;
                editor.putString(SHARE_FILE3, picPath);
                view.setImageBitmap(BitmapFactory.decodeFile(picPath));
                editor.commit();
                LSLog.Log_INFO(String.format("%s,path:%s",SHARE_FILE1,picPath));
            }
        }
    }


    private boolean isInit()
    {
        boolean isinit=false;
        SharedPreferences tempUpload= getSharedPreferences(SHARE_FILENAME, MODE_PRIVATE);
        Map<String,?> res = tempUpload.getAll();
        String pic1=(String) res.get(SHARE_FILE1);
        String pic2=(String) res.get(SHARE_FILE2);
        String pic3=(String) res.get(SHARE_FILE3);

        if(pic1==null && pic2==null && pic3==null)
        {
            isinit=true;
        }
        return isinit;
    }


    //region getcontent
    public class GetContent implements LSContentResolver.VoidHandler
    {
        private int mcode;
        public GetContent(int code)
        {
            mcode=code;
        }
        @Override
        public void doit()
        {
            Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, mcode);
        }
    }
    //endregion

    //region state template
    private interface IState
    {
        void UIControl();
        void uploadPic();
    }


    private  class InitState implements IState
    {
        @Override
        public void UIControl()
        {
            mMyControls.mBtnUpload.setEnabled(true);
            mMyControls.mBtnBack.setEnabled(true);
            mMyControls.mBtnGiveup.setEnabled(false);
        }

        @Override
        public void uploadPic()
        {
            myUploadPic();
        }
    }

    private class UploadingState implements IState
    {
        @Override
        public void UIControl()
        {
            mMyControls.mBtnUpload.setEnabled(false);
            mMyControls.mBtnBack.setEnabled(false);
            mMyControls.mBtnGiveup.setEnabled(false);
        }

        @Override
        public void uploadPic()
        {
            //do nothing
        }
    }

    private class StageState implements IState
    {
        @Override
        public void UIControl()
        {
            mMyControls.mBtnUpload.setEnabled(true);
            mMyControls.mBtnBack.setEnabled(true);
            mMyControls.mBtnGiveup.setEnabled(true);
        }

        @Override
        public void uploadPic()
        {
            myUploadPic();
        }
    }

    private void myUploadPic()
    {
        //1.检测到未上传的图片。2.利用okhttp的http协议上传图片。

        SharedPreferences share= getSharedPreferences(SHARE_FILENAME, MODE_PRIVATE);
        String strpic1= share.getString(SHARE_FILE1,"" );
        final File file=new File(strpic1);

        Thread thread=new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if(file!=null && file.exists())
                {
                    LSOKHttp.postFile("http://192.168.0.106/upload/Default.aspx", null, file);
                }
            }
        });
        thread.start();
    }
    //endregion

    //region The class of FindControls
    private MyControls mMyControls=null;
    public class MyControls
    {
        private ImageView mImageView24;
        private ImageView mImageView25;
        private ImageView mImageView26;
        private Button mBtnUpload;
        private Button mBtnBack;
        private Button mBtnGiveup;
        private TextView mTvPic1;
        private TextView mTvPic2;
        private TextView mTvPic3;

        public MyControls()
        {
            mImageView24 = (ImageView) findViewById(R.id.imageView24);
            mImageView25 = (ImageView) findViewById(R.id.imageView25);
            mImageView26 = (ImageView) findViewById(R.id.imageView26);
            mBtnUpload = (Button) findViewById(R.id.btn_upload);
            mBtnBack = (Button) findViewById(R.id.btn_back);
            mBtnGiveup = (Button) findViewById(R.id.btn_giveup);
            mTvPic1 = (TextView) findViewById(R.id.tv_pic1);
            mTvPic2 = (TextView) findViewById(R.id.tv_pic2);
            mTvPic3 = (TextView) findViewById(R.id.tv_pic3);
        }
    }
    //endregion
}