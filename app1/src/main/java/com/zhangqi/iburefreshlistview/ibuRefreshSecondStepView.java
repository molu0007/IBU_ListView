package com.zhangqi.iburefreshlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhangqi.meituanrefreshlistview.R;

public class ibuRefreshSecondStepView extends View{

	private Bitmap endBitmap,scaledBitmap;

	public ibuRefreshSecondStepView(Context context, AttributeSet attrs,
									int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ibuRefreshSecondStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ibuRefreshSecondStepView(Context context) {
		super(context);
		init();
	}

	private void init() {
		endBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.img_huojian2), 89, 110, false);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(widthMeasureSpec) * endBitmap.getHeight() / endBitmap.getWidth());
	}
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
							int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		scaledBitmap = Bitmap.createScaledBitmap(endBitmap, 89, 110, false);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(endBitmap, 90, dip2px(getContext(), 80 * 1), null);

	}
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	private int measureWidth(int widthMeasureSpec){
		int result = 0;
		int size = MeasureSpec.getSize(widthMeasureSpec);
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		if (mode == MeasureSpec.EXACTLY) {
			result = size;
		}else {
			result = endBitmap.getWidth();
			if (mode == MeasureSpec.AT_MOST) {
				result = Math.min(result, size);
			}
		}
		return result;
	}
}
