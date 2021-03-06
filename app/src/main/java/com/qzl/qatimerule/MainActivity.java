package com.qzl.qatimerule;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.hdl.elog.ELog;
import com.qzl.timerule.RulerView;
import com.qzl.timerule.TipView;
import com.qzl.timerule.bean.OnBarMoveListener;
import com.qzl.timerule.bean.OnSelectedTimeListener;
import com.qzl.timerule.bean.TimeSlot;
import com.qzl.timerule.utils.CUtils;
import com.qzl.timerule.utils.DateUtils;
import com.qzl.timerule.utils.TimeHelper;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RulerView tRuler;
    private TextView tvTime;
    private long date;
    private TextView tvProgress;
    private FrameLayout llP;
    private RelativeLayout llH;
    private TipView tvTip;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = TimeHelper.getTimeMillis("2021-11-17 00:00:00");
        initView();
        setListener();
//        tRuler.setCurrentTimeMillis(System.currentTimeMillis());
//              tRuler.setCurrentTimeMillis(System.currentTimeMillis()-1*24*60*60*1000L);
    }

    private void initView() {
        tRuler = (RulerView) findViewById(R.id.tr_line);
        tRuler.setCurrentTimeMillis(time);
        tvProgress = (TextView) findViewById(R.id.tv_progress);
        tvTime = (TextView) findViewById(R.id.tv_time);
        llP = (FrameLayout) findViewById(R.id.ll_porental);
        llH = (RelativeLayout) findViewById(R.id.ll_lanspace);
        tvTip = (TipView) findViewById(R.id.tv_tip);
    }

    private void setListener() {
        tRuler.setOnSelectedTimeListener(new OnSelectedTimeListener() {
            @Override
            public void onDragging(long startTime, long endTime) {
                tvProgress.setText(DateUtils.getTime(startTime) + "-" + DateUtils.getTime(endTime));
            }

            @Override
            public void onMaxTime() {
                Toast.makeText(MainActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMinTime() {
                Toast.makeText(MainActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
            }
        });
        tRuler.setOnBarMoveListener(new OnBarMoveListener() {
            @Override
            public void onDragBar(boolean isLeftDrag, long currentTime) {
//                ELog.e("??????");
//                ELog.e("isLeftDrag(?????????)=" + isLeftDrag);
//                ELog.e("currentTime" + DateUtils.getDateTime(currentTime));
            }

            @Override
            public void onBarMoving(long currentTime) {
//                ELog.e("currentTime" + DateUtils.getDateTime(currentTime));
                currentTimeMillis = currentTime;
            }

            @Override
            public void onBarMoveFinish(long currentTime) {
                ELog.e("????????????" + DateUtils.getDateTime(currentTime));
//                Toast.makeText(MainActivity.this, "????????????", Toast.LENGTH_SHORT).show();
            }

            /**
             * ????????????????????????
             */
            @Override
            public void onMoveExceedStartTime() {
                ELog.e("?????????????????????");
            }

            /**
             * ????????????????????????
             */
            @Override
            public void onMoveExceedEndTime() {
                ELog.e("?????????????????????");
            }

            /**
             * ?????????????????????
             */
            @Override
            public void onMaxScale() {

            }

            /**
             * ?????????????????????
             */
            @Override
            public void onMinScale() {

            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        date = tRuler.getCurrentTimeMillis();
                        tvTime.setText(DateUtils.getDateByCurrentTiem(date) + " " + DateUtils.getTime(date));
                    }
                });
            }
        }, 0, 1000);
        List<TimeSlot> times = new ArrayList<>();
//        times.add(new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()), DateUtils.getTodayStart(System.currentTimeMillis()) - 60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 120 * 60 * 1000));
//        times.add(new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()), DateUtils.getTodayStart(System.currentTimeMillis()) + 3 * 60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 4 * 60 * 60 * 1000));
        //times.add(new TimeSlot(DateUtils.getTodayStart(time), DateUtils.getTodayStart(System.currentTimeMillis()) - 5 * 60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 7 * 60 * 60 * 1000));
        times.add(new TimeSlot(
            DateUtils.getTodayStart(TimeHelper.getTimeMillis("2021-11-16 00:00:00")), TimeHelper.getTimeMillis("2021-11-16 23:00:00") , TimeHelper.getTimeMillis("2021-11-16 23:59:00")));
        times.add(new TimeSlot(
            DateUtils.getTodayStart(System.currentTimeMillis()),
            TimeHelper.getTimeMillis("2021-11-17 00:00:00"),
            TimeHelper.getTimeMillis("2021-11-17 00:10:00")));
        times.add(new TimeSlot(DateUtils.getTodayStart(TimeHelper.getTimeMillis("2021-11-18 00:00:00")),
            TimeHelper.getTimeMillis("2021-11-18 00:00:00"),
            TimeHelper.getTimeMillis("2021-11-18 00:10:00")));
        Log.e("hdltag", "setListener(MainActivity.java:121):" + times);
        tRuler.setVedioTimeSlot(times);
    }

    public void toTestPage(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    public void toAdd(View view) {
        tRuler.setCurrentTimeMillis(System.currentTimeMillis());
//        tRuler.setCurrentTimeMillis(DateUtils.getTodayStart(System.currentTimeMillis()) + 23 * 60 * 60 * 1000);
//        tRuler.setCurrentTimeMillis(DateUtils.getTodayStart(System.currentTimeMillis()) + 20 * 60 * 1000);
    }

    boolean isMove = true;

    public void toMoveOrPause(View view) {
        isMove = !isMove;
        if (isMove) {
            tRuler.openMove();
        } else {
            tRuler.closeMove();
        }
    }

    /**
     * ??????????????????
     *
     * @param view
     */
    public void showVedioArea(View view) {
        List<TimeSlot> times = new ArrayList<>();
        times.add(new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()), DateUtils.getTodayStart(System.currentTimeMillis()) + 60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 120 * 60 * 1000));
        times.add(new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()), DateUtils.getTodayStart(System.currentTimeMillis()) + 3 * 60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 4 * 60 * 60 * 1000));
        tRuler.setVedioTimeSlot(times);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            llH.removeAllViews();
            llH.setVisibility(View.GONE);
            llP.setVisibility(View.VISIBLE);
            llP.addView(tRuler);
            tvTip.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            llP.addView(tvTip);
        } else {
            llP.removeAllViews();
            llP.setVisibility(View.GONE);
            llH.setVisibility(View.VISIBLE);
            llH.addView(tRuler);
            tvTip.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CUtils.dip2px(75)));
            llH.addView(tvTip);
        }
        Log.e("hdltag", "onConfigurationChanged(MainActivity.java:175):" + currentTimeMillis);
        ELog.e("????????????" + DateUtils.getDateTime(currentTimeMillis));
        tRuler.setCurrentTimeMillis(currentTimeMillis);
    }

    boolean isSelected = true;

    public void toSelected(View view) {
        tRuler.setSelectTimeArea(isSelected);
        isSelected = !isSelected;
    }

    public void getSelected(View view) {
        ELog.e("tRuler.getSelectStartTime()=" + DateUtils.getDateByCurrentTiem(tRuler.getSelectStartTime()) + "  " + DateUtils.getTime(tRuler.getSelectStartTime()));
        tvProgress.setText(DateUtils.getTime(tRuler.getSelectStartTime()) + "-" + DateUtils.getTime(tRuler.getSelectEndTime()));
    }

    public void onHalfScreen(View view) {
//        currentTimeMillis = tRuler.getCurrentTimeMillis();
        ELog.e(DateUtils.getDateTime(currentTimeMillis));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//?????????????????????
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//???????????????
    }

    private long currentTimeMillis;

    public void onExitHalfScreen(View view) {
//        currentTimeMillis = tRuler.getCurrentTimeMillis();
        ELog.e(DateUtils.getDateTime(currentTimeMillis));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//???????????????
    }

    public void onShowOrHideRight(View view) {
        tvTip.setShowRightTip(true);
    }

    public void onShowOrHideLeft(View view) {
        tvTip.setShowLeftTip(true);
    }

    public void onShowOrHideLeftLandscape(View view) {
        tvTip.setShowLeftTipLandscape(true);
    }

    public void onShowOrHideRightLandscape(View view) {
        tvTip.setShowRightTipLandscape(true);
    }
}
