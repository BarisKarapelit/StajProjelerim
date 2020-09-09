package com.bariskarapelit.stajprojesversion2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MultiTouchControl implements View.OnTouchListener {

    private ViewGroup root;
    private ComponentView componentView;
    private float circleSize = 100;
    private int duration = 1500;
    private List<Vector3> fingerTips = new ArrayList<>();
    private boolean enabled = true;

    public MultiTouchControl(ViewGroup root, ComponentView componentView, float circleSize, int duration) {
        this.root = root;
        this.componentView = componentView;
        this.circleSize = circleSize;
        this.duration = duration;
    }

    public MultiTouchControl(ViewGroup root, ComponentView componentView) {
        this.root = root;
        this.componentView = componentView;
    }


    // Dinlemeyi başlatıyor
    public void startListener(){
        root.setOnTouchListener(this);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    //Parmaklarını algılanmasını sağlıyor
    public boolean onTouch(View v, MotionEvent event) {
        if (!enabled)
            return false;
        final int points = event.getPointerCount();
        int removedPoint = -1;
        final int action = event.getAction() & MotionEvent.ACTION_MASK;
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            fingerTips.clear();
            return false;
        } //Parmağın kaldırdıktan sonra  konum verisi siliyor
        if (action == MotionEvent.ACTION_POINTER_UP || action == MotionEvent.ACTION_UP)
            removedPoint = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                    >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        // Dokulan yerin koordinatlarını siliyor
        fingerTips.clear();

        for (int i = 0; i < points; i++) {
            int pointerID = event.getPointerId(i);
            if (pointerID == MotionEvent.INVALID_POINTER_ID) {
                continue;
            }
            if (removedPoint == i) {
                continue;
            }
            fingerTips.add(new Vector3(event.getX(i), event.getY(i), pointerID));
        }
        for (Vector3 fingerTip : fingerTips) {
            createCircle(fingerTip.x, fingerTip.y);
        }

        return true;
    }

    // İmageview konum verisini belirliyor
     //Interface de imageview oluşturuyor
    private void createCircle(float x, float y) {
        if (componentView != null) {
            View view = componentView.onCreateComponent();
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setX(x - circleSize / 2);
            view.setY(y - circleSize / 2);
            params.width = (int) circleSize;
            params.height = (int) circleSize;
            view.setLayoutParams(params);
            root.addView(view);
            dieAfter(view);
        }
    }
    //dieAfter circle ları yoketmek için
    private void dieAfter(final View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                root.removeView(view);
            }
        }, duration);
    }

    // x , y ve id değerini tutuyor
    static class Vector3 {
        public float x, y;
        public int id;

        public Vector3(float x, float y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    //MainActivity den gönderdiğin imageviewei veya ne gönderiyorsan onu almak için
    interface ComponentView {
        View onCreateComponent();
    }

    public ViewGroup getRoot() {
        return root;
    }

    public MultiTouchControl setRoot(ViewGroup root) {
        this.root = root;
        return this;
    }

    public ComponentView getComponentView() {
        return componentView;
    }

    public MultiTouchControl setComponentView(ComponentView componentView) {
        this.componentView = componentView;
        return this;
    }

    public float getCircleSize() {
        return circleSize;
    }


    // X
    public MultiTouchControl setCircleSize(float circleSize) {
        this.circleSize = circleSize;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public MultiTouchControl setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    // MultiTouch Aktif olmasını sağlıyor
    public MultiTouchControl setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
