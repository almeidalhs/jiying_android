package com.corelib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.seawind.corelib.R;

public class RoundProgressBar extends View {
	// 环的宽度
	private float roundWidth = (float) 20.00;
	// 环的底色
	private int backgroundColor = 0;
	// 进度环颜色
	private int progressColor = 0;
	// 圆环背景
	private int roundBackground = 0;
	// 最大进度值
	private int maxProgress = 100;
	// 进度值
	private int progress = 0;

	public RoundProgressBar(Context context) {
		super(context);
	}

	public RoundProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initXmlData(context, attrs);
	}

	public RoundProgressBar(Context context, AttributeSet attrs,
							int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initXmlData(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(progress>maxProgress)
		{
			progress = maxProgress;
		}
		int centre = getWidth() / 2; // 获取圆心的x坐标
		int radius = (int) (centre - roundWidth / 2); // 圆环的半径

		Paint outerPaint = new Paint();
		outerPaint.setColor(backgroundColor); // 设置圆环的颜色
		outerPaint.setStyle(Paint.Style.STROKE); // 设置空心
		outerPaint.setStrokeWidth(roundWidth); // 设置圆环的宽度
		outerPaint.setAntiAlias(true); // 消除锯齿
		canvas.drawCircle(centre, centre, radius, outerPaint); // 画出圆环

		Paint interPaint = new Paint();
		interPaint.setStrokeWidth(roundWidth); // 设置圆环的宽度
		interPaint.setColor(progressColor); // 设置进度的颜色
		outerPaint.setAntiAlias(true);
		RectF oval = new RectF(centre - radius, centre - radius, centre
				+ radius, centre + radius);
		interPaint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(oval, -90, 360 * progress / maxProgress, false,
				interPaint); // 根据进度画圆弧

		Paint pointPaint = new Paint();
		pointPaint.setColor(progressColor);
		pointPaint.setStrokeWidth(roundWidth / 2);
		pointPaint.setAntiAlias(true);
		pointPaint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(getWidth() / 2, roundWidth / 2, roundWidth / 4,
				pointPaint);

		float cx = 0.0000f;
		float cy = 0.0000f;
		float angle = 360 * progress / maxProgress;
		if ((angle >= 0) && (angle <= 90)) {
			cy = (float) (getHeight() / 2 - radius
					* Math.cos(angle * Math.PI / 180));
			cx = (float) (getWidth() / 2 + radius
					* Math.sin(angle * Math.PI / 180));
		} else if (angle <= 180) {
			cy = (float) (getHeight() / 2 + radius
					* Math.cos((180 - angle) * Math.PI / 180));
			cx = (float) (getWidth() / 2 + radius
					* Math.sin((180 - angle) * Math.PI / 180));
		} else if (angle <= 270) {
			cx = (float) (getWidth() / 2 - radius
					* Math.sin((angle - 180) * Math.PI / 180));
			cy = (float) (getHeight() / 2 + radius
					* Math.cos((angle - 180) * Math.PI / 180));
		} else if (angle <= 360) {
			cx = (float) (getWidth() / 2 - radius
					* Math.sin((360 - angle) * Math.PI / 180));
			cy = (float) (getHeight() / 2 - radius
					* Math.cos((360 - angle) * Math.PI / 180));
		}
		canvas.drawCircle(cx, cy, roundWidth / 4, pointPaint);

	}

	private void initXmlData(Context context, AttributeSet attrs) {
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);
		roundWidth = mTypedArray.getDimension(
				R.styleable.RoundProgressBar_roundProgressWidth, 20);
		backgroundColor = mTypedArray
				.getColor(R.styleable.RoundProgressBar_backgroundColor, context
						.getResources().getColor(R.color.round_backgroundColor));
		progressColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_progressColor, context
						.getResources().getColor(R.color.round_progressColor));
		roundBackground = mTypedArray
				.getColor(R.styleable.RoundProgressBar_roundBackground, context
						.getResources().getColor(R.color.round_roundBackground));
		maxProgress = mTypedArray.getInteger(
				R.styleable.RoundProgressBar_maxProgress, 100);
		progress = mTypedArray.getInteger(
				R.styleable.RoundProgressBar_progress, 0);
	}

	public void setProgress(int p)
	{
		this.progress = p;
		invalidate();
	}

	public void setMaxProgress(int m)
	{
		this.maxProgress = m;
		invalidate();
	}

	public void setBackgroundColor(int bc)
	{
		this.backgroundColor = bc;
		invalidate();
	}

	public void setProgressColor(int pc)
	{
		this.progressColor = pc;
		invalidate();
	}

	public void setRoundWidth(int rw)
	{
		this.roundWidth = rw;
		invalidate();
	}
}
