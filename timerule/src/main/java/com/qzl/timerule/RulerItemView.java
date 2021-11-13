package com.qzl.timerule;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;


import com.qzl.timerule.bean.ScaleMode;
import com.qzl.timerule.bean.TimeSlot;
import com.qzl.timerule.utils.CUtils;
import com.qzl.timerule.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class RulerItemView extends View {
    /**
     * 刻度配置
     */
    private Paint smallRulerPaint = new Paint();//小刻度画笔
    private int rulerColor = 0xffb5b5b5;//刻度的颜色
    private int rulerWidthSamll = CUtils.dip2px(0.5f);//小刻度的宽度
    private int rulerHeightSamll = CUtils.dip2px(10);//小刻度的高度
    /**
     * 大刻度
     */
    private Paint largeRulerPaint = new Paint();//大刻度画笔
    private int rulerWidthBig = CUtils.dip2px(0.5f);//大刻度的宽度
    /**
     * 上下两条线
     */
    private Paint upAndDownLinePaint = new Paint();//刻度画笔
    private int upAndDownLineWidth = CUtils.dip2px(1);//上下两条线的宽度
    private int upAndDownLineColor = rulerColor;

    /**
     * 文本画笔
     */
    private TextPaint keyTickTextPaint = new TextPaint();
    private int textColor = 0xff444242;//文本颜色
    private int textSize = CUtils.dip2px(12);//文本大小
    /**
     * 视频区域画笔
     */
    private Paint vedioAreaPaint = new Paint();
    private int vedioBg = 0xFF03DAC5;//视频背景颜色
    private RectF vedioAreaRect = new RectF();


    private int viewHeight = CUtils.dip2px(80);
    //除数、刻度精度
    private int divisor;

    public RulerItemView(Context context) {
        this(context, null);
    }

    public RulerItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setDivisor();
        initPaint();
    }

    /**
     * 设置精度
     */
    private void setDivisor() {
        switch (scaleMode) {
            case KEY_HOUSE:
                divisor = 10;
                break;
            case KEY_MINUTE:
            default:
                divisor = 1;
        }
    }

    private ScaleMode scaleMode = ScaleMode.KEY_MINUTE;

    public ScaleMode getScaleMode() {
        return scaleMode;
    }

    public void setScaleMode(ScaleMode scaleMode) {
        this.scaleMode = scaleMode;
        setDivisor();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        smallRulerPaint.setAntiAlias(true);
        smallRulerPaint.setColor(rulerColor);
        smallRulerPaint.setStrokeWidth(rulerWidthSamll);

        largeRulerPaint.setAntiAlias(true);
        largeRulerPaint.setColor(rulerColor);
        largeRulerPaint.setStrokeWidth(rulerWidthBig);

        keyTickTextPaint.setAntiAlias(true);
        keyTickTextPaint.setColor(textColor);
        keyTickTextPaint.setTextSize(textSize);

        vedioAreaPaint.setAntiAlias(true);
        vedioAreaPaint.setColor(vedioBg);

        upAndDownLinePaint.setAntiAlias(true);
        upAndDownLinePaint.setColor(upAndDownLineColor);
        upAndDownLinePaint.setStrokeWidth(upAndDownLineWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawVedioArea(canvas);
        drawUpAndDownLine(canvas);
        drawRuler(canvas);
    }

    /**
     * 画视频区域
     * @param canvas
     */
    private void drawVedioArea(Canvas canvas) {
        for (TimeSlot timeSlot : vedioTimeSlot) {
            //1、首先判断是否全部包含了本时间段
            boolean isContainTime = DateUtils.isContainTime(timeSlot, timeIndex * 60 * 1000, timeIndex * 60 * 1000 + 10 * 60 * 1000);
            boolean isLeftTime = DateUtils.isCurrentTimeArea(timeSlot.getStartTimeMillis(), timeIndex * 60 * 1000, timeIndex * 60 * 1000 + 10 * 60 * 1000);
            boolean isRightTime = DateUtils.isCurrentTimeArea(timeSlot.getEndTimeMillis(), timeIndex * 60 * 1000, timeIndex * 60 * 1000 + 10 * 60 * 1000);
            if (isContainTime) {//包含所有（画整个item）
                vedioAreaRect.set(0, 0, getWidth(), viewHeight - textSize * 2);
                canvas.drawRect(vedioAreaRect, vedioAreaPaint);
//                return;
            } else if (isLeftTime && isRightTime) {//两端都在（画左边时刻到右边时刻）
                float distanceX1 = (timeSlot.getStartTimeMillis() - timeIndex * 60 * 1000) * (getWidth() / (10 * 60 * 1000f));
                float distanceX2 = (timeSlot.getEndTimeMillis() - timeIndex * 60 * 1000) * (getWidth() / (10 * 60 * 1000f));
                vedioAreaRect.set(distanceX1, 0, distanceX2, viewHeight - textSize * 2);
                canvas.drawRect(vedioAreaRect, vedioAreaPaint);
//                return;
            } else if (isLeftTime) {//只有左边在（左边时刻开始到item结束都画）
                float distanceX = (timeSlot.getStartTimeMillis() - timeIndex * 60 * 1000) * (getWidth() / (10 * 60 * 1000f));
                vedioAreaRect.set(distanceX, 0, getWidth(), viewHeight - textSize * 2);
                canvas.drawRect(vedioAreaRect, vedioAreaPaint);
//                return;
            } else if (isRightTime) {//只有右边在（画从头开始到右边时刻）
                float distanceX = (timeSlot.getEndTimeMillis() - timeIndex * 60 * 1000) * (getWidth() / (10 * 60 * 1000f));
                vedioAreaRect.set(0, 0, distanceX, viewHeight - textSize * 2);
                canvas.drawRect(vedioAreaRect, vedioAreaPaint);
//                return;
            }
        }
    }

    /**
     * 画刻度尺
     *
     * @param canvas
     */
    private void drawRuler(Canvas canvas) {
        float viewWidth = getWidth();
        float itemWidth = viewWidth / (10 / divisor);
        float rightX = 0;
        if (scaleMode == ScaleMode.KEY_HOUSE) {
            //小时级别的画法
            if ((timeIndex / 10) % 6 == 0) { //大刻度
                //画上面的大刻度
                canvas.drawLine(0, 0, 0, rulerHeightSamll * 2, largeRulerPaint);
                //画下面的大刻度
                canvas.drawLine(0, viewHeight - textSize * 2 - upAndDownLineWidth, 0, viewHeight - rulerHeightSamll * 2 - textSize * 2 - upAndDownLineWidth, largeRulerPaint);
                float timeStrWidth = keyTickTextPaint.measureText(DateUtils.getHourMinute(timeIndex));
//                canvas.drawText(DateUtils.getHourMinute(timeIndex), timeStrWidth + rulerWidthSamll, viewHeight + CUtils.dip2px(14), keyTickTextPaint);
                canvas.drawText(DateUtils.getHourMinute(timeIndex), -timeStrWidth / 2, viewHeight - textSize, keyTickTextPaint);
            } else {//小刻度
                //画上面的小刻度
                canvas.drawLine(0, 0, 0, rulerHeightSamll, smallRulerPaint);
                //画下面的小刻度
                canvas.drawLine(0, viewHeight - textSize * 2 - upAndDownLineWidth, 0, viewHeight - rulerHeightSamll - textSize * 2 - upAndDownLineWidth, smallRulerPaint);
            }
        } else {
            for (int i = 0; i < 60; i++) {
                if (i == 0 || i == 59) {
                    //画上面的大刻度
                    if (i == 0) {
                        canvas.drawLine(rightX, 0, rightX, rulerHeightSamll * 2, smallRulerPaint);
                        //画下面的大刻度
                        canvas.drawLine(rightX, viewHeight - textSize * 2 - upAndDownLineWidth, rightX, viewHeight - rulerHeightSamll * 2 - textSize * 2 - upAndDownLineWidth, smallRulerPaint);
                        rightX += itemWidth;
//                        ELog.e("viewHeght = " + viewHeight);
//                        ELog.e("viewHeght = " + viewHeight);
                        float timeStrWidth = keyTickTextPaint.measureText(DateUtils.getHourMinute(timeIndex));
                        canvas.drawText(DateUtils.getHourMinute(timeIndex), -timeStrWidth / 2, viewHeight - textSize, keyTickTextPaint);
                    }
                } else if (i % divisor == 0) {
                    //画上面的小刻度
                    canvas.drawLine(rightX, 0, rightX, rulerHeightSamll, largeRulerPaint);
                    //画下面的小刻度
                    canvas.drawLine(rightX, viewHeight - textSize * 2 - upAndDownLineWidth, rightX, viewHeight - rulerHeightSamll - textSize * 2 - upAndDownLineWidth, largeRulerPaint);
                    rightX += itemWidth;
                }
            }
        }
    }

    /**
     * 画上下两条线
     *
     * @param canvas
     */
    private void drawUpAndDownLine(Canvas canvas) {
        int viewWidth = getWidth();
        //画上下两条线
        canvas.drawLine(0, upAndDownLineWidth / 2, viewWidth, upAndDownLineWidth / 2, upAndDownLinePaint);
        canvas.drawLine(0, viewHeight - textSize * 2 - upAndDownLineWidth / 2, viewWidth, viewHeight - textSize * 2 - upAndDownLineWidth / 2, upAndDownLinePaint);
    }

    /**
     * 设置当前时间（处于第几个小时）
     *
     * @param index
     */
    public void setCurTimeIndex(int index) {
        timeIndex = index * 10;
    }

    private int timeIndex;
    /**
     * 视频时间段集合
     */
    private List<TimeSlot> vedioTimeSlot = new ArrayList<>();

    /**
     * 获取视频时间段
     *
     * @return
     */
    public List<TimeSlot> getVedioTimeSlot() {
        return vedioTimeSlot;
    }

    /**
     * 设置视频时间段
     *
     * @param vedioTimeSlot
     */
    public void setVedioTimeSlot(List<TimeSlot> vedioTimeSlot) {
        this.vedioTimeSlot.clear();
        this.vedioTimeSlot.addAll(vedioTimeSlot);
        postInvalidate();//重绘
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
//        ELog.e("设置高度了"+viewHeight);
        postInvalidate();
    }
}
