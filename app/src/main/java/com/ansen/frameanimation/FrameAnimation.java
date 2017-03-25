package com.ansen.frameanimation;

import android.widget.ImageView;

/**
 * Created by Ansen on 2015/5/14 23:30.
 *
 * @E-mail: tomorrow_p@163.com
 * @Blog: http://blog.csdn.net/qq_25804863
 * @Github: https://github.com/ansen360
 * @PROJECT_NAME: FrameAnimation
 * @PACKAGE_NAME: com.ansen.frameanimation.sample
 * @Description: TODO
 */
public class FrameAnimation {

    private boolean mIsRepeat;

    private AnimationListener mAnimationListener;

    private ImageView mImageView;

    private int[] mFrameRess;

    /**
     * 每帧动画的播放间隔数组
     */
    private int[] mDurations;

    /**
     * 每帧动画的播放间隔
     */
    private int mDuration;

    /**
     * 下一遍动画播放的延迟时间
     */
    private int mDelay;

    private int mLastFrame;

    private boolean mNext;


    /**
     * @param iv       播放动画的控件
     * @param frameRes 播放的图片数组
     * @param duration 播放的时间间隔
     * @param isRepeat 是否重复播放
     */
    public FrameAnimation(ImageView iv, int[] frameRes, int duration, boolean isRepeat) {
        this.mImageView = iv;
        this.mFrameRess = frameRes;
        this.mDuration = duration;
        this.mLastFrame = frameRes.length - 1;
        this.mIsRepeat = isRepeat;
        play(0);
    }

    /**
     * @param iv        播放动画的控件
     * @param frameRess 播放的图片数组
     * @param durations 播放的时间间隔(每张图片的播放时间间隔)
     * @param isRepeat  是否重复播放
     */
    public FrameAnimation(ImageView iv, int[] frameRess, int[] durations, boolean isRepeat) {
        this.mImageView = iv;
        this.mFrameRess = frameRess;
        this.mDurations = durations;
        this.mLastFrame = frameRess.length - 1;
        this.mIsRepeat = isRepeat;
        playByDurations(0);
    }

    /**
     * @param iv        播放动画的控件
     * @param frameRess 播放的图片数组
     * @param duration  播放的时间间隔(每张图片的播放时间间隔)
     * @param delay     下一遍动画播放的延迟时间
     */
    public FrameAnimation(ImageView iv, int[] frameRess, int duration, int delay) {
        this.mImageView = iv;
        this.mFrameRess = frameRess;
        this.mDuration = duration;
        this.mDelay = delay;
        this.mLastFrame = frameRess.length - 1;
        playAndDelay(0);
    }

    /**
     * @param iv        播放动画的控件
     * @param frameRess 播放的图片数组
     * @param durations 播放的时间间隔(每张图片的播放时间间隔)
     * @param delay     下一遍动画播放的延迟时间
     */
    public FrameAnimation(ImageView iv, int[] frameRess, int[] durations, int delay) {
        this.mImageView = iv;
        this.mFrameRess = frameRess;
        this.mDurations = durations;
        this.mDelay = delay;
        this.mLastFrame = frameRess.length - 1;
        playByDurationsAndDelay(0);
    }

    private void playByDurationsAndDelay(final int i) {
        mImageView.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (0 == i) {
                    if (mAnimationListener != null) {
                        mAnimationListener.onAnimationStart();
                    }
                }
                mImageView.setBackgroundResource(mFrameRess[i]);
                if (i == mLastFrame) {
                    if (mAnimationListener != null) {
                        mAnimationListener.onAnimationRepeat();
                    }
                    mNext = true;
                    playByDurationsAndDelay(0);
                } else {
                    playByDurationsAndDelay(i + 1);
                }
            }
        }, mNext && mDelay > 0 ? mDelay : mDurations[i]);

    }

    private void playAndDelay(final int i) {
        mImageView.postDelayed(new Runnable() {

            @Override
            public void run() {
                mNext = false;
                if (0 == i) {
                    if (mAnimationListener != null) {
                        mAnimationListener.onAnimationStart();
                    }
                }
                mImageView.setBackgroundResource(mFrameRess[i]);
                if (i == mLastFrame) {
                    if (mAnimationListener != null) {
                        mAnimationListener.onAnimationRepeat();
                    }
                    mNext = true;
                    playAndDelay(0);
                } else {
                    playAndDelay(i + 1);
                }
            }
        }, mNext && mDelay > 0 ? mDelay : mDuration);

    }

    private void playByDurations(final int i) {
        mImageView.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (0 == i) {
                    if (mAnimationListener != null) {
                        mAnimationListener.onAnimationStart();
                    }
                }
                mImageView.setBackgroundResource(mFrameRess[i]);
                if (i == mLastFrame) {
                    if (mIsRepeat) {
                        if (mAnimationListener != null) {
                            mAnimationListener.onAnimationRepeat();
                        }
                        playByDurations(0);
                    } else {
                        if (mAnimationListener != null) {
                            mAnimationListener.onAnimationEnd();
                        }
                    }
                } else {

                    playByDurations(i + 1);
                }
            }
        }, mDurations[i]);

    }

    private void play(final int i) {
        mImageView.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (0 == i) {
                    if (mAnimationListener != null) {
                        mAnimationListener.onAnimationStart();
                    }
                }
                mImageView.setBackgroundResource(mFrameRess[i]);
                if (i == mLastFrame) {

                    if (mIsRepeat) {
                        if (mAnimationListener != null) {
                            mAnimationListener.onAnimationRepeat();
                        }
                        play(0);
                    } else {
                        if (mAnimationListener != null) {
                            mAnimationListener.onAnimationEnd();
                        }
                    }

                } else {

                    play(i + 1);
                }
            }
        }, mDuration);
    }

    public static interface AnimationListener {

        /**
         * <p>Notifies the start of the animation.</p>
         */
        void onAnimationStart();

        /**
         * <p>Notifies the end of the animation. This callback is not invoked
         * for animations with repeat count set to INFINITE.</p>
         */
        void onAnimationEnd();

        /**
         * <p>Notifies the repetition of the animation.</p>
         */
        void onAnimationRepeat();
    }

    /**
     * <p>Binds an animation listener to this animation. The animation listener
     * is notified of animation events such as the end of the animation or the
     * repetition of the animation.</p>
     *
     * @param listener the animation listener to be notified
     */
    public void setAnimationListener(AnimationListener listener) {
        this.mAnimationListener = listener;
    }

}
