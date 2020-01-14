package com.linson.android.hiandroid2.UI;

import android.media.AudioManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.CalendarView;
import android.widget.SeekBar;

import com.linson.android.hiandroid2.R;

import app.lslibrary.androidHelper.LSContentResolver;
import app.lslibrary.androidHelper.LSLog;
import app.lslibrary.androidHelper.LSSystemServices;

public class OtherControls extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_controls);


        mMyControls=new MyControls();

        LSSystemServices.StreamVolumeInfo streamVolumeInfo=LSSystemServices.getVolumeInfo(this, AudioManager.STREAM_MUSIC);
        mMyControls.mSeekBar.setMax(streamVolumeInfo.max);
        mMyControls.mSeekBar.setProgress(streamVolumeInfo.now);
        mMyControls.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                LSSystemServices.setVolume(AudioManager.STREAM_MUSIC,OtherControls.this ,progress );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

    }




//region FindControls Helper
private MyControls mMyControls=null;
public class MyControls implements View.OnClickListener
{
    private SeekBar mSeekBar;
    private SeekBar mSeekBar2;
    private CalendarView mCalendarView;
    private SearchView mSvSv1;

    public MyControls()
    {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mSvSv1 = (SearchView) findViewById(R.id.sv_sv1);
    }

    @Override
    public void onClick(View v)
    {
        //you can bind click event in here , and then put click event 's function into activity.
        //if (v.getId() == xxx){}
    }
}
//endregion

}