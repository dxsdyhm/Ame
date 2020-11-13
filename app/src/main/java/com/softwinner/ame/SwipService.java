package com.softwinner.ame;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

/**
 * @Author: dxs
 * @time: 2020/11/12
 * @Email: duanxuesong12@126.com
 */
public class SwipService extends AccessibilityService {
    private Path pathDown = new Path();
    private Path pathUp = new Path();

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        initPath();
        Log.e("dxsTest", "onServiceConnected:");
    }

    @Override
    public void onInterrupt() {
        Log.e("dxsTest", "onInterrupt:");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.e("dxsTest", "accessibilityEvent:" + accessibilityEvent.toString());
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.e("KeyEvent", "event:" + event);
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    dragDowm();
                    break;
                case KeyEvent.KEYCODE_VOLUME_UP:
                case KeyEvent.KEYCODE_DPAD_UP:
                    dragUp();
                    break;
            }
        }
        return super.onKeyEvent(event);
    }

    private void dragDowm() {
        dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription
                (pathDown, 0, 500)).build(), null, null);
    }

    private void dragUp() {
        dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription
                (pathUp, 0, 500)).build(), null, null);
    }

    private void initPath() {
        pathUp.moveTo(300, 50);
        pathUp.lineTo(300, 650);

        pathDown.moveTo(300, 650);
        pathDown.lineTo(300, 50);
    }
}
