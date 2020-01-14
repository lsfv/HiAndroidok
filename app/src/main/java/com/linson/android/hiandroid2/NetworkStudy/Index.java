package com.linson.android.hiandroid2.NetworkStudy;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.LSLibrary.Network.LSOKHttp;
import com.linson.android.hiandroid2.R;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.lslibrary.androidHelper.LSLog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Index extends AppCompatActivity implements View.OnClickListener
{
    //auto generate
    private Button mBtnGet2;
    private Button mBtnGet;
    private TextView mTvMsg;
    private Button mBtnDownload;
    private Button mButton26;
    private Button mBtnUpload;


    private void findControls()
    {
        mBtnGet2 = (Button) findViewById(R.id.btn_get2);
        mBtnGet = (Button) findViewById(R.id.btn_get);
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        mBtnDownload = (Button) findViewById(R.id.btn_download);
        mButton26 = (Button) findViewById(R.id.button26);
        mBtnUpload = (Button) findViewById(R.id.btn_upload);
    }
    //auto generate

    private final String jsonUrl = "http://120.79.79.80:8044/MainPage.asmx/Category_MainCategory";
    private final String jsonpostUrl="http://120.79.79.80:8044/MainPage.asmx/Supplier_Eat";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index8);

        findControls();
        setControlsEvent();
    }

    private void setControlsEvent()
    {
        mBtnGet2.setOnClickListener(this);
        mBtnGet.setOnClickListener(this);
        mTvMsg.setOnClickListener(this);
        mBtnDownload.setOnClickListener(this);
        mButton26.setOnClickListener(this);
        mBtnUpload.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_get:
            {
                //request,2.callback for string ,or callback for fail.
                LSOKHttp.get(jsonUrl, new Callback()
                {
                    @Override
                    public void onFailure(Call call, IOException e)
                    {
                        LSComponentsHelper.LS_Log.Log_Exception(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException
                    {
                        String data=response.body().string();
                        Gson theGson=new GsonBuilder().create();
                        CategoryList categorys=theGson.fromJson(data, CategoryList.class);
                        LSComponentsHelper.LS_Log.Log_INFO(categorys.VCategory_All.size()+"");
                    }
                });
                break;
            }
            case R.id.btn_get2:
            {
                Map<String,String> body=new HashMap<>();
                body.put("cityid", "360300");
                LSOKHttp.post(jsonpostUrl, body, new Callback()
                {
                    @Override
                    public void onFailure(Call call, IOException e)
                    {
                        LSComponentsHelper.LS_Log.Log_Exception(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException
                    {
                        String msg=response.body().string();
                        LSComponentsHelper.LS_Log.Log_INFO(msg);
                    }
                });
                break;
            }
            case R.id.btn_download:
            {
                download();
                break;
            }
            case R.id.button26:
            {
                //同步的观察者模式。
                //为什么订阅的时候。就触发事件，不是应该订阅归订阅，之后再额外提供触发函数吗？这样观察者还要立即调用函数，而不是由订阅者自己注册和注销。
                //rejava不是标准的观察者模式，主要还是一个反应式编程，并且让异步变得简单而已，不过个人不喜欢。放弃。
                //ClickRxJava();


                ClickRxJavaAsync();
                break;
            }
            case R.id.btn_upload:
            {
                LSComponentsHelper.LS_Activity.startActivity(this, MyUpload.class);
            }
        }
    }

    private void ClickRxJavaAsync()
    {
        //这里传递回调过去。其实okhttp，本就有异步的回调。很多例子只是简单举一个含有异步的回调的库来显示rejava的使用。真是完全无语。
        //rejava 的作用当然是异步处理，但是不体现在直接和okhttp的调用上。

        getCategoryList()
                .subscribeOn(Schedulers.io())
            .subscribe(new Action1<CategoryList>()
            {
                @Override
                public void call(CategoryList categoryListA)
                {
                    LSComponentsHelper.LS_Log.Log_INFO(categoryListA.VCategory_All.size()+"");
                }
            });
    }

    private Observable<CategoryList> getCategoryList()
    {
        Observable<CategoryList> res=Observable.create(new Observable.OnSubscribe<CategoryList>()
        {
            @Override
            public void call(Subscriber<? super CategoryList> subscriber)
            {
                final CategoryList[] categoryList = {new CategoryList()};
                try
                {
                    Response res = LSOKHttp.getAsyn(jsonUrl);
                    String data=res.body().string();
                    Gson theGson=new GsonBuilder().create();
                    categoryList[0]=theGson.fromJson(data, CategoryList.class);
                }
                catch (Exception e)
                {
                    LSLog.Log_Exception(e);
                }
                subscriber.onNext(categoryList[0]);
            }
        });

        return res;
    }




    //总体来说，语法结构如下：observable.subscribe(observer);
    //有2个大对象，可观察者和观察者。不准确翻译的话中文很容易误解，其实中文用观者者表示可观察者，用订阅者表示观察者，更符合中文的理解。
    //什么时候中文开发圈能强大起来，翻译外语的不准确有时候比这个知识点本身更费解。
    //一.建立可观察者。通用Observable.create  .可以用from.
    //二。建立观察者implements Observer<>,实现Observer就可以了。或者实现 Observer的派生虚拟类：abstract class Subscriber<T>。
    //      或者实现Action1,其实就是实现一个Observer，再把Action1的实现给Observer的onnext接口。因为他们定义的接口都是void doit(T t);
    //三。再通过subscribe，就可以把观察者传递给观察者，类似于注册订阅，并触发事件。
    private void ClickRxJava()
    {
        //1.create observable with create.
        Observable<String> myObservable=Observable.create(new Observable.OnSubscribe<String>()
        {
            @Override
            public void call(Subscriber<? super String> subscriber)
            {
                //假设获得数据是个耗时的过程，需要3秒 。
                try
                {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    LSLog.Log_Exception(e);
                }
                subscriber.onNext("linson");
                //假设获得数据是个耗时的过程，需要3秒 。
                try
                {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    LSLog.Log_Exception(e);
                }
                subscriber.onNext("tony");
                //假设获得数据是个耗时的过程，需要3秒 。
                try
                {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    LSLog.Log_Exception(e);
                }
                subscriber.onNext("pan");
            }
        });

        myObservable.subscribe(new MyAction());
        myObservable.subscribe(new MyAction2());
        myObservable.subscribe(new MyObserver());

        // 2.create observable with from
        //String[] names={"linson","tony"};
        //Observable.from(names).subscribe(new MyAction());
        //Observable.from(names).subscribe(new MyAction2());
        //Observable.from(names).subscribe(new MyObserver());

        //3.subscribe 就像是notifiseOrder.通知订阅者，事件已经发生了。
    }

    public static class MyAction implements Action1<String>
    {
        @Override
        public void call(String s)
        {
            LSLog.Log_INFO("Teacher1 eating my breakfest .please waiting....:"+s);
        }
    }

    public static class MyAction2 implements Action1<String>
    {
        @Override
        public void call(String s)
        {
            LSLog.Log_INFO("Teacher2:,"+s+"let me look at you..emm.......");

        }
    }


    public static class MyObserver implements Observer<String>
    {
        @Override
        public void onCompleted()
        {
            LSLog.Log_INFO();
        }

        @Override
        public void onError(Throwable e)
        { }

        @Override
        public void onNext(String s)
        {
            LSLog.Log_INFO("Teacher3:"+s+".dont speak!");
        }
    }

    //dowanload:什么协议？http ftp? if http:1.会先得到大小？如果有大小。那就是一直收，直到收到大小。或者断掉。
    private void download()
    {


    }


//innder class
public static class CategoryList
{
    public @Nullable List<Category> VCategory_All=new ArrayList<>();
}

    public static class Category
    {
        public int cg_id;
        public int cg_fatherid;
        public String cg_name;
        public String cg_tablename;
        public String cg_pic;
        public @Nullable String cg_extend;
    }
}