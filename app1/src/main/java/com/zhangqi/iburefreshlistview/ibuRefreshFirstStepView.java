package com.zhangqi.iburefreshlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.zhangqi.meituanrefreshlistview.R;

public class ibuRefreshFirstStepView extends View{

	private Bitmap initialBitmap;
	private float mCurrentProgress;
	private Bitmap scaledBitmap;

	public ibuRefreshFirstStepView(Context context, AttributeSet attrs,
								   int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ibuRefreshFirstStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ibuRefreshFirstStepView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		//这个就是那个火箭图片
		initialBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.img_huojian1));

	}

	/**
	 * 重写onMeasure方法主要是设置wrap_content时 View的大小
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//根据设置的宽度来计算高度  设置为符合第二阶段娃娃图片的宽高比例
		setMeasuredDimension(measureWidth(widthMeasureSpec),measureWidth(widthMeasureSpec)*initialBitmap.getHeight()/initialBitmap.getWidth());
	}

	/**
	 * 当wrap_content的时候，宽度即为第二阶段娃娃图片的宽度
	 * @param widMeasureSpec
	 * @return
	 */
	private int measureWidth(int widMeasureSpec){
        int result = 0;
        int size = MeasureSpec.getSize(widMeasureSpec);
        int mode = MeasureSpec.getMode(widMeasureSpec);
        if (mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = initialBitmap.getWidth();
            if (mode == MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }
		return result;
        }

	/**
	 * 在onLayout里面获得测量后View的宽高
	 * @param changed
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		// 给火箭图片进行等比例的缩放
		scaledBitmap = Bitmap.createScaledBitmap(initialBitmap,89,110, false);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//这个方法是对画布进行缩放，从而达到椭圆形图片的缩放，第一个参数为宽度缩放比例，第二个参数为高度缩放比例，
//		canvas.scale(mCurrentProgress, mCurrentProgress, measuredWidth/2, measuredHeight/2);
		//将等比例缩放后的椭圆形画在画布上面
		canvas.drawBitmap(scaledBitmap,90,dip2px(getContext(),80*mCurrentProgress),null);
		
	}
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	/**
	 * 设置缩放比例，从0到1  0为最小 1为最大
	 * @param currentProgress
	 */
	public void setCurrentProgress(float currentProgress){
		mCurrentProgress = currentProgress;
	}
	
	
}
