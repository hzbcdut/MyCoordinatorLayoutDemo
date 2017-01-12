package hzb.cdut.com.mycoordinatorlayoutdemo.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhongkun on 2016/11/22.
 */

public class ScrollToHideFooterBehavior extends CoordinatorLayout.Behavior {

    private int mDySinceDirectionChange;
    private boolean mIsHidden = false;

    public ScrollToHideFooterBehavior() {
    }

    public ScrollToHideFooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        //不再需要滑动隐藏
//        if (dy > 0 && mDySinceDirectionChange < 0
//                || dy < 0 && mDySinceDirectionChange > 0) {
//            mDySinceDirectionChange = 0;
//        }
//
//        Log.e("Behavior", "onNestedPreScroll: " + mDySinceDirectionChange);
//        mDySinceDirectionChange += dy;
//
//        if (mDySinceDirectionChange > child.getHeight()
//                && !mIsHidden) {
//            hide(child);
//        } else if (mDySinceDirectionChange < 0
//                && mIsHidden) {
//            show(child);
//        }
//        Log.e("Behavior", "onNestedPreScroll: " + dy + ", " + mDySinceDirectionChange + ", " + mIsHidden
//                + ", " + child.getHeight());
    }

    boolean setInitialPaddingTop = false;
    int initialPaddingTop;
    @NonNull
    @Override
    public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorLayout, View child, WindowInsetsCompat insets) {
//        LogUtil.d(Contant.DEBUG_LOG, "onApplyWindowInsets: " + insets.getSystemWindowInsetBottom()
//                + ", " + insets.getSystemWindowInsetLeft()
//                + ", " + insets.getSystemWindowInsetRight()
//                + ", " + insets.getSystemWindowInsetTop());
//        LogUtil.d(Contant.DEBUG_LOG, "onApplyWindowInsets:Stable " + insets.getStableInsetBottom()
//                + ", " + insets.getStableInsetLeft()
//                + ", " + insets.getStableInsetRight()
//                + ", " + insets.getStableInsetTop());

        if (!setInitialPaddingTop) {
            setInitialPaddingTop = true;
            initialPaddingTop = child.getPaddingTop();
        }
//        LogUtil.d(Contant.DEBUG_LOG, "onApplyWindowInsets: initialPaddingTop" + initialPaddingTop);
        WindowInsetsCompat insetsAfterConsume = insets.replaceSystemWindowInsets(insets.getSystemWindowInsetLeft(), 0, insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
        return insetsAfterConsume;
    }

    private void show(View child) {
        mIsHidden = false;
        ViewCompat.animate(child)
                .alpha(1)
                .translationY(0)
                .setDuration(200)
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(View view) {
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    private void hide(View child) {
        mIsHidden = true;
        ViewCompat.animate(child)
                .cancel();
        ViewCompat.animate(child)
                .alpha(0)
                .translationY(child.getHeight())
                .setDuration(200)
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(View view) {
                        view.setVisibility(View.GONE);
                    }
                })
                .start();
    }
}
