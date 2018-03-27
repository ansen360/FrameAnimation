[![](https://jitpack.io/v/ansen360/FrameAnimation.svg)](https://jitpack.io/#ansen360/FrameAnimation)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/68590d8a37394b2ca7298c5ddadd2310)](https://www.codacy.com/app/ansen360/FrameAnimation?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ansen360/FrameAnimation&amp;utm_campaign=Badge_Grade)
[![](https://img.shields.io/badge/author-ansen-ff69b4.svg)](http://blog.csdn.net/qq_25804863?viewmode=contents)

**2015年项目接到一个需求,实现一个向导动画,这个动画一共六十张图片,当时使用的是全志A33的开发(512的内存),通过使用Android的动画集实现,效果特别卡顿,然后想到这种方式来实现,效果很流畅.然后写成开一个开源项目供大家参考**

**对比以下两种方式实现帧动画,使用相同的80张280x280的png图片执行动画,资源占用情况对比:**

![](http://oma689k8f.bkt.clouddn.com/note/3/3.png)

**Android动画集实现: 内存占用56M左右**

**FrameAnimation实现: 内存占用4M左右**


两者CUP占用都比较低,可忽略

**代码下载:**
**https://github.com/ansen360/FrameAnimation**

##### Sample效果:
![](http://oma689k8f.bkt.clouddn.com/note/3/4.gif)
http://oma689k8f.bkt.clouddn.com/note/3/4.gif
##### 一、Android动画集实现帧动画
- 1 在drawable目录下创建动画集animalist.xml
```
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false">

    <!--通过Android动画集播放的八十张图片-->
    <item
        android:drawable="@mipmap/c_1"
        android:duration="50" />
    <item
        android:drawable="@mipmap/c_2"
        android:duration="50" />
     <!--  省略...  -->
    <item
        android:drawable="@mipmap/circle_19"
        android:duration="50" />
    <item
        android:drawable="@mipmap/circle_20"
        android:duration="50" />

</animation-list>
```

- 2 在布局文件ImageView中使用该drawable
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.ansen.frameanimation.sample.MainActivity">

    <ImageView
        android:id="@+id/image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/animlist" />

</LinearLayout>

```
- 3 在代码中调用,启动动画
```
    ImageView image = (ImageView) findViewById(R.id.image);

    AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();
        animationDrawable.start();
```
**动画启动系统资源占用情况如下:**

![](http://oma689k8f.bkt.clouddn.com/note/3/1.png)
手动触发GC,内存占用几乎没改变



##### 二、FrameAnimation实现帧动画
- 1 定义需要播放动画的资源文件;在arrays文件中定义资源,或者在代码中定义
```
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <!--通过FrameAnimation播放的八十张图片-->
    <array name="c">

        <item>@mipmap/c_1</item>
        <item>@mipmap/c_2</item>
        <!-- 省略...  -->
        <item>@mipmap/circle_19</item>
        <item>@mipmap/circle_20</item>
    </array>

</resources>
```
获取定义之后的资源数组(代码中可直接定义资源文件的数组,便可忽略上一步):
```
    private int[] getRes() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.c);
        int len = typedArray.length();
        int[] resId = new int[len];
        for (int i = 0; i < len; i++) {
            resId[i] = typedArray.getResourceId(i, -1);
        }
        typedArray.recycle();
        return resId;
    }
```
- 2 在代码中调用,启动动画
```
    ImageView image = (ImageView) findViewById(R.id.image);

    FrameAnimation frameAnimation = new FrameAnimation(image, getRes(), 50, true);
    frameAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
        @Override
        public void onAnimationStart() {
            Log.d(TAG, "start");
        }

        @Override
        public void onAnimationEnd() {
            Log.d(TAG, "end");
        }

        @Override
        public void onAnimationRepeat() {
            Log.d(TAG, "repeat");
        }
    });
```

**动画启动系统资源占用情况如下:**

![](http://oma689k8f.bkt.clouddn.com/note/3/2.png)
手动触发GC,内存占用有明显变化

[博客地址](http://blog.csdn.net/qq_25804863/article/details/65634864)
