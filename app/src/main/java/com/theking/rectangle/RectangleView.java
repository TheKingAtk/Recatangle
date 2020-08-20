package com.theking.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RectangleView extends View {
    float left=10,right=30,top=10,bottom=30;
    final float TOUCH_SENSITIVITY = 50;
    float iX,iY;
    int h,w;
    Paint paint=new Paint();
    public RectangleView(Context context) {
        super(context);
    }

    public RectangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        h=MeasureSpec.getSize(heightMeasureSpec);
        w=MeasureSpec.getSize(widthMeasureSpec);
        left= (float) 100;
        right=(float)w-100;
        //Toast.makeText(getContext(), String.valueOf(w-100), Toast.LENGTH_SHORT).show();
        top=(float)600;
        bottom=(float)h-600;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(left,bottom,right,top,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                iX=x;
                iY=y;
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                if(Math.abs(iX-left)<=TOUCH_SENSITIVITY) {
                    left=x;
                    invalidate();
                    iX=x;
                }
                else if(Math.abs(iX-right)<=TOUCH_SENSITIVITY) {
                    right=x;
                    invalidate();
                    iX=x;
                }
                if(Math.abs(iY-top)<=TOUCH_SENSITIVITY) {
                    top=y;
                    invalidate();
                    iY=y;
                }
                else  if(Math.abs(iY-bottom)<=TOUCH_SENSITIVITY) {
                bottom=y;
                invalidate();
                iY=y;
                }
                if((iX-Math.min(left, right))>TOUCH_SENSITIVITY && (Math.max(left, right)-iX)>TOUCH_SENSITIVITY
                && (iY-Math.min(top, bottom))>TOUCH_SENSITIVITY && (Math.max(top, bottom)-iY)>TOUCH_SENSITIVITY) {
                    left+=(x-iX);
                    right+=(x-iX);
                    top+=(y-iY);bottom+=(y-iY);
                    invalidate();
                    iX=x;
                    iY=y;
                }

                break;
        }
        return true;
    }
}
