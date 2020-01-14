package com.linson.android.hiandroid2.Pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linson.android.hiandroid2.R;


//个人觉得从android本身条件来说这样已经是mvc了。
//但googler觉得control从view中诞生，就不是纯mvc了.看解释 todo项目的解释
//The separation between Activity and Fragment fits nicely with this
//implementation of MVP: the Activity is the overall controller that creates and connects views and presenters.
//因为view是变化的，control是稳定的，所以view应该从control中初始化。
//我们看下理想的模式，当一个外部页面需要跳转到某页面，名字为abc.activity. abc.activity应该是个代号，它的view应该可以整体被替换。
//意思就是abc.activity中应该可以控制要装载哪个view。
// 但是android设计的缺陷。activity本初就不是设计为一个control，而是view和contol的综合体。因为只有activity才有setContentView这个方法。setContentView无法独立工作。这。。。
//为了mvc ，必须有个fragment ,个人无好感，但是还是试一下，进行对比。
public class MyMVP2 extends AppCompatActivity implements View.OnClickListener,MyMVP2_contract.Iview
{
    private EditText mEtName;
    private Button mBtnSubmit;
    private TextView mTvMsg;
    private TextView mTvTitle;

    private MyMVP2_contract.IControl mControl;


    private void findControls()
    {
        mEtName = (EditText) findViewById(R.id.et_name);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mTvMsg = (TextView) findViewById(R.id.tv_msg);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_submit:
            {
                String str = mEtName.getText().toString().trim();
                //这里为什么不返回string，再自己给textview赋值？而要装b的写个方法setStrInfo来实现接口?
                //因为view的目的就是清除逻辑。view并不知道要做什么。他的接口需求完全是control提出来的。
                //所以setStrInfo是control提出来的。view才实现。而不是我们故意把setStrInfo分出来装b。
                //这里我们调用contril的getStrLength，我们并不知道control的逻辑会去调用setStrInfo。我们只是实现了接口定义的setStrInfo这个方法。
                //复杂了很多，当然从某种意义上也简洁了。所谓的某种意义就是项目足够大，view和control可以由2方人马分别开发。否则一个人自我搞精分自己，有点。。。
                mControl.getStrLength(str);
            }
            default:
            {
                break;
            }
        }
    }

    //mvc含有一个特点，
    //view 负责内容显示和用户操作反馈，
    //control负责处理逻辑并在处理逻辑中，在界面方面的操作上对view提出的接口需求。 当然也负责和model沟通。
    //model 负责数据模型的逻辑，通常还有另外一个数据获得帮助类。
    @Override
    public void disPlayStrInfo(String str)
    {
        mTvMsg.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        mControl=new Control(this);

        findControls();
        mTvTitle.setText("mymvp2");
    }


    //region control
    private static class Control implements MyMVP2_contract.IControl
    {
        private layer_business layer_business = new layer_business();
        private MyMVP2_contract.Iview mIview;


        public Control(MyMVP2_contract.Iview view)
        {
            mIview=view;
        }

        @Override
        public void getStrLength(String str)
        {
            String info=layer_business.getStringInfo(str);
            mIview.disPlayStrInfo(info);
        }
    }
//endregion
}