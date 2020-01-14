//package com.linson.android.hiandroid2.Services.AIDLSys;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.linson.android.hiandroid2.R;
//
//
//
////android把跨进程通讯的解决方案命令为aidl，其实技术都是相通的，这个和微软的10年前的远程技术基本一模一样。连命名都带有l。language。 :)
////底层支撑都是靠接口来表达意图，传输必定是tcp或utp。就是看效率和使用方便程度的区别了。
////测试将使用2个例子，一个基本数据类型，不需要序列化数据，实现平方的函数，来测试aidl是否运行正常。
////之后再建立一个game的数据模型作为参数。来测试序列化。可能是一个简单方法，修改下数据的某个字段的值就ok。
////因为是跨进程。所以阻塞和非阻塞是个重要的属性。掉用2个方法，有返回值，和没返回值。sleep。看是否都会延迟。就可以判断是否阻塞。
////进一步，2个方法，第一个sleep更久。客户端直接执行2个方法。并打印他们的返回值。来进一步验证阻塞问题。
////看看是否有非阻塞方法。也就是我们常见的callback的异步处理 方式。
//public class SysAidl extends AppCompatActivity implements View.OnClickListener
//{
//    private Button mBtnIpc5;
//    private Button mBtnIpc4;
//    private Button mBtnIpc3;
//    private Button mBtnIpc2;
//    private Button mBtnIpc;
//
//
//    //region  findcontrols and bind click event.
//    private void findControls()
//    {   //findControls
//        mBtnIpc5 = (Button) findViewById(R.id.btn_ipc5);
//        mBtnIpc4 = (Button) findViewById(R.id.btn_ipc4);
//        mBtnIpc3 = (Button) findViewById(R.id.btn_ipc3);
//        mBtnIpc2 = (Button) findViewById(R.id.btn_ipc2);
//        mBtnIpc = (Button) findViewById(R.id.btn_ipc);
//
//        //set event handler
//        mBtnIpc.setOnClickListener(this);
//        mBtnIpc2.setOnClickListener(this);
//        mBtnIpc3.setOnClickListener(this);
//        mBtnIpc4.setOnClickListener(this);
//        mBtnIpc5.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v)
//    {
//        switch (v.getId())
//        {
//
//            default:
//            {
//                break;
//            }
//        }
//    }
//    //endregion
//
//    //region other member variable
//    //endregion
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sys_aidl);
//
//        findControls();
//    }
//}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           