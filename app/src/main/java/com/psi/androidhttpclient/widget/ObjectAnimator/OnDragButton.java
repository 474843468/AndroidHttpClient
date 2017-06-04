package com.psi.androidhttpclient.widget.ObjectAnimator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import com.psi.androidhttpclient.R;


public class  OnDragButton extends SurfaceView implements SurfaceHolder.Callback,OnTouchListener{

  private Context mContext;
  private SurfaceHolder mSurfaceHolder;
  private Bitmap mIcon;
  private Paint mPaint;
  private boolean mIsRunning;
  private Rect mRect;
  private Point mPoint = new Point();
  private boolean isDrag = false;
  private String mText;
  private AttributeSet mSet;
  public OnDragButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    mContext = context;
    mSet= attrs;
    initSurface();
  }


  public OnDragButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    mContext = context;
    mSet= attrs;
    initSurface();
  }

  public OnDragButton(Context context,String text) {
    super(context);
    mContext = context;
    mText = text;
    initSurface();
  }

  private void initSurface() {
    mSurfaceHolder = getHolder();
    mSurfaceHolder.addCallback(this);
    this.setOnTouchListener(this);
    TypedArray array = mContext.obtainStyledAttributes(mSet,  R.styleable.OnDragButton);
    mText = array.getString(R.styleable.OnDragButton_title);
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width,
      int height) {

  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.back);
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mIsRunning = true;
    mRect  = new Rect(0, 0, mIcon.getWidth(), mIcon.getHeight());
    String familyName = "宋体";

    Typeface font = Typeface.create(familyName,Typeface.BOLD);
    mPaint.setColor(Color.RED);
    mPaint.setTypeface(font);
    mPaint.setTextSize(22);
    onDrag();
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    mIsRunning = false;
  }

  private int offsetX,offsetY;
  @Override
  public boolean onTouch(View v, MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mPoint.x = (int)event.getX();
        mPoint.y = (int)event.getY();
        if (mRect.contains(mPoint.x,mPoint.y)) {
          isDrag = true;
          offsetX = mPoint.x - mRect.left;
          offsetY = mPoint.y - mRect.top;
        }
        break;
      case MotionEvent.ACTION_UP:
        isDrag = false;
        break;
      case MotionEvent.ACTION_MOVE:

        if (isDrag) {
          mRect.left = (int)event.getX() - offsetX;
          mRect.top = (int)event.getY() - offsetY;
          mRect.right = mRect.left + mIcon.getWidth();
          mRect.bottom = mRect.top + mIcon.getHeight();
          if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right =  mRect.left + mIcon.getWidth();
          }

          if (mRect.right > getMeasuredWidth()) {
            mRect.right = getMeasuredWidth();
            mRect.left = mRect.right - mIcon.getWidth();
          }

          if (mRect.top < 0) {
            mRect.top = 0 ;
            mRect.bottom = mRect.top + mIcon.getHeight();
          }

          if (mRect.bottom > getMeasuredHeight()) {
            mRect.bottom = getMeasuredHeight();
            mRect.top = mRect.bottom - mIcon.getHeight();
          }
          onDrag();
        }
        break;
      default:
        break;
    }
    return true;
  }

  public void onDrag(){
    Canvas canvas = mSurfaceHolder.lockCanvas();
    canvas.drawColor(Color.BLACK);
    canvas.drawBitmap(mIcon, mRect.left, mRect.top, null);
    canvas.drawText(mText, mRect.left, mRect.top+30, mPaint);
    mSurfaceHolder.unlockCanvasAndPost(canvas);

  }

  public void setText(String Text){
    mText = Text;
  }
}
