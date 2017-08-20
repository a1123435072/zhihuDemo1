package com.example.yangg.zhihu;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangg on 2017/7/5.
 */

public class ScaleBehavior extends FloatingActionButton.Behavior {
    private boolean isAnimation = false;

    public ScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //判断如果说是向下就让他隐藏
        if ((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed > 0) && !isAnimation) {
            /**
             * 告诉BottomSheetBehaiour隐藏
             */
            if (onStateChangeListener!=null){
                onStateChangeListener.onChanged(false);
            }
            AnimatorUtil.scaleShow(child, listener);

        } else if (((dyConsumed < 0 && dyUnconsumed == 0) || dxConsumed == 0 && dxUnconsumed < 0) && !isAnimation) {
            if (onStateChangeListener!=null){
                onStateChangeListener.onChanged(true);
            }
            AnimatorUtil.scaleHide(child, listener);
        }
    }

    public static <V extends View> ScaleBehavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params)
                .getBehavior();
        if (!(behavior instanceof ScaleBehavior)) {
            throw new IllegalArgumentException(
                    "The view is not associated with ScaleBehavior");
        }
        return (ScaleBehavior) behavior;
    }
    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener){
        this.onStateChangeListener = onStateChangeListener;
    }


    public OnStateChangeListener onStateChangeListener ;
    public interface  OnStateChangeListener{
        void onChanged(boolean isShow);
    }
    public ViewPropertyAnimatorListener listener = new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view) {

            isAnimation = true;
        }

        @Override
        public void onAnimationEnd(View view) {

            isAnimation = false;
        }

        @Override
        public void onAnimationCancel(View view) {
            isAnimation = false;
        }
    };
}
