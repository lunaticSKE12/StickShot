package com.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.skykai.stickercamera.R;


/**
 * @author tongqian.ni
 */
public class LabelSelector extends LinearLayout {

    //    private ImageView txtLabelBtn;
//    private ImageView addrLabelBtn;
    private TextView addText;
    private float mLastTouchX = -1;
    private float mLastTouchY = -1;

    public LabelSelector(Context context) {
        this(context, null);
    }

//    public void setAddrClicked(OnClickListener listener) {
//        addrLabelBtn.setOnClickListener(listener);
//    }

    public LabelSelector(Context context, AttributeSet attr) {
        super(context, attr);
        LayoutInflater.from(context).inflate(R.layout.view_label_layout, this);
        addText = (TextView) findViewById(R.id.tv_addText);
//        txtLabelBtn = (ImageView) findViewById(R.id.iv_tag_tip);
//        addrLabelBtn = (ImageView) findViewById(R.id.iv_tag_address);
    }

    public void setTxtClicked(OnClickListener listener) {
        addText.setOnClickListener(listener);
    }

    public float getmLastTouchX() {
        return mLastTouchX;
    }

    public float getmLastTouchY() {
        return mLastTouchY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:// 手指离开时 
            case MotionEvent.ACTION_CANCEL:
                mLastTouchX = event.getX();
                mLastTouchY = event.getY();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void showToTop() {
        setVisibility(View.VISIBLE);
        bringToFront();
    }

    public void hide() {
        setVisibility(View.GONE);
    }

}
