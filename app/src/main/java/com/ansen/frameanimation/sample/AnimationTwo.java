package com.ansen.frameanimation.sample;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.ansen.frameanimation.FrameAnimation;
import com.ansen.frameanimation.R;

/**
 * Created by Ansen on 2017/3/24 10:09.
 *
 * @E-mail: tomorrow_p@163.com
 * @Blog: http://blog.csdn.net/qq_25804863
 * @Github: https://github.com/ansen360
 * @PROJECT_NAME: FrameAnimation
 * @PACKAGE_NAME: com.ansen.frameanimation.sample
 * @Description: TODO
 */
public class AnimationTwo extends Activity {


    private static final String TAG = "ansen";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);
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


//        每次动画播完延迟4000毫秒再次播放
//        FrameAnimation frameAnimation = new FrameAnimation(image, getRes(), 50, 4000);
    }

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
}
