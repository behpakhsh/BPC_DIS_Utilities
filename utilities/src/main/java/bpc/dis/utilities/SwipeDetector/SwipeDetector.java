package bpc.dis.utilities.SwipeDetector;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {

    private int min_distance = 150;
    private float downX;
    private float downY;
    private View view;

    private onSwipeEvent swipeEventListener;

    public SwipeDetector(View view) {
        this.view = view;
        view.setOnTouchListener(this);
    }

    public void setOnSwipeListener(onSwipeEvent listener) {
        try {
            swipeEventListener = listener;
        } catch (ClassCastException e) {
            Log.e("ClassCastException", "please pass SwipeDetector.onSwipeEvent Interface instance", e);
        }
    }

    public void onRightToLeftSwipe() {
        if (swipeEventListener != null)
            swipeEventListener.SwipeEventDetected(view, SwipeTypeEnum.RIGHT_TO_LEFT);
        else
            Log.e("SwipeDetector error", "please pass SwipeDetector.onSwipeEvent Interface instance");
    }

    public void onLeftToRightSwipe() {
        if (swipeEventListener != null)
            swipeEventListener.SwipeEventDetected(view, SwipeTypeEnum.LEFT_TO_RIGHT);
        else
            Log.e("SwipeDetector error", "please pass SwipeDetector.onSwipeEvent Interface instance");
    }

    public void onTopToBottomSwipe() {
        if (swipeEventListener != null)
            swipeEventListener.SwipeEventDetected(view, SwipeTypeEnum.TOP_TO_BOTTOM);
        else
            Log.e("SwipeDetector error", "please pass SwipeDetector.onSwipeEvent Interface instance");
    }

    public void onBottomToTopSwipe() {
        if (swipeEventListener != null)
            swipeEventListener.SwipeEventDetected(view, SwipeTypeEnum.BOTTOM_TO_TOP);
        else
            Log.e("SwipeDetector error", "please pass SwipeDetector.onSwipeEvent Interface instance");
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                float upX = event.getX();
                float upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > min_distance) {
                        if (deltaX < 0) {
                            this.onLeftToRightSwipe();
                            return true;
                        }
                        if (deltaX > 0) {
                            this.onRightToLeftSwipe();
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    if (Math.abs(deltaY) > min_distance) {
                        if (deltaY < 0) {
                            this.onTopToBottomSwipe();
                            return true;
                        }
                        if (deltaY > 0) {
                            this.onBottomToTopSwipe();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }

                return true;
            }
        }
        return false;
    }

    public SwipeDetector setMinDistanceInPixels(int min_distance) {
        this.min_distance = min_distance;
        return this;
    }

    public enum SwipeTypeEnum {
        RIGHT_TO_LEFT, LEFT_TO_RIGHT, TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }

    public interface onSwipeEvent {
        void SwipeEventDetected(View v, SwipeTypeEnum SwipeType);
    }

}