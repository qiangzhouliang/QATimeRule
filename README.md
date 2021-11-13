# TimeRuler

时间轴、时间刻度尺
- 继承至TextureView，效率更高
- 已适配横竖屏
- 缩放功能（分钟、小时级别）
- 自动移动（自由决定开启与关闭移动）
- 时间轴中选择时间
- 实时设置当天时间
- 显示有效视频时间
- 超时（超过00:00:00,、23:59:59）自动处理
- 带拖动开始、结束、自动移动、超时回调
- 带时间选择回调
- 属性自由配置

## 使用

### 混淆配置

```pro
#timeruler
-keep class com.qzl.timerule.**{*;}
-dontwarn com.qzl.timerule.**
```


### 开启硬件加速

所在activity需要开启硬件加速(建议配置横竖屏不重新走一遍生命周期)

 ```xml
    <activity
       android:configChanges="orientation|keyboardHidden|screenSize"
       android:hardwareAccelerated="true">
    </activity>
 ```

### 布局
> TextureView本身不支持直接设置背景颜色（android:background="color"，设置之后会抛出异常），可以通过属性viewBackgroundColor来设置背景色

最简单的使用（属性使用默认值）

```xml
<com.qzl.timerule.RulerView
    android:id="@+id/tr_line"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_centerInParent="true"
    android:clipChildren="false"
    android:clipToPadding="false"/>
```

配置属性（更多属性说明见本文附录）

```xml
<com.qzl.timerule.RulerView
    android:id="@+id/tr_line"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_centerInParent="true"
    android:clipChildren="false"
    android:clipToPadding="false"
    app:centerLineColor="@color/colorAccent"
    app:viewHeightVer="120dp"/>

```

### 设置当前时间

```java
tRuler.setCurrentTimeMillis(设置中心线的时间)
```

### 初始化视频时间段

```java
        List<TimeSlot> times = new ArrayList<>();
        times.add(new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()),DateUtils.getTodayStart(System.currentTimeMillis()) + 60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 120 * 60 * 1000));
        times.add(new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()),DateUtils.getTodayStart(System.currentTimeMillis()) + 3*60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 4*60 * 60 * 1000));
        tRuler.setVedioTimeSlot(times);
```

### 是否自动移动
```java
    tRuler.openMove();//打开移动
    tRuler.closeMove();//关闭移动
```

### 关于横竖屏适配

#### 为什么要适配？

由于横竖屏切换之后，view宽高不能保持一致导致需要适配

#### 适配步骤

1、定义一个全局的当前时间毫秒值

```java
 private long currentTimeMillis;
```

2、在onBarMoving回调方法中记录currentTimeMillis

```java
tRuler.setOnBarMoveListener(new OnBarMoveListener() {
             ...
            @Override
            public void onBarMoving(long currentTime) {
                currentTimeMillis = currentTime;
            }
          ...
        });
```

3、在时间轴所在activity/fragment中重写onConfigurationChanged方法

```java
  @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ...
        tRuler.setCurrentTimeMillis(currentTimeMillis);
        ...
    }
```

>通过以上三个步骤即可适配横竖屏（手动与自动横竖屏都可以）


## 附表

### 布局文件属性配置说明
> 所有属性都有默认值，可以不设置

- 中轴线颜色-->centerLineColor
- 含有录像背景颜色-->vedioAreaColor
- 选择时间的边框颜色-->selectTimeBorderColor
- 已选择时间颜色-->selectTimeAreaColor
- 选择时间边框大小-->selectTimeBorderSize
- 竖屏时view高度-->viewHeightVer
- 横屏时view高度-->viewHeightHor

## 版本记录
