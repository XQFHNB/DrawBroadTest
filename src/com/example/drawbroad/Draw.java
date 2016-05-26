package com.example.drawbroad;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

/**清理 锯齿有两种方法，一种是在画笔中进行清理，一种是在画布中进行清理
 * 
 */
public class Draw extends SurfaceView implements Callback, OnTouchListener {
	private Paint pen = new Paint();// 准备画笔
	private Path path = new Path();// 光有画笔不行，我们还要有路径

	/**
	 * 自定义控件必须的构造方法，构造方法中肯定对字段进行初始化
	 * 
	 * @param context
	 * @param attrs
	 */
	public Draw(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this);// 我们使用surfaceview就肯定要添加一个回调方法
		pen.setColor(Color.RED);// 设置画笔颜色
		pen.setTextSize(10);// 设置画笔字体大小
		pen.setStyle(Style.STROKE);// 设置画笔的风格，是实心还是空心的，空心就是STROKE
		setOnTouchListener(this);
	}

	/**
	 * 创建一个绘制方法，在这个方法中我们要绘制肯定就要有一个画布，因此按照规矩我们拿到一个canvas
	 */
	public void draw() {
		Canvas canvas = getHolder().lockCanvas();// 因为实在surfaceview中，因此我们需要通过getholder来锁定画布
		canvas.drawColor(Color.WHITE);// 首先初始化画布的颜色，我们可以初始化为白色
		canvas.drawPath(path, pen);// 这里我们通过这个方法来进行绘制，第一个参数为drawpath对象，第二个参数为画笔对象
		getHolder().unlockCanvasAndPost(canvas);// 绘制完成后我们要解锁画布
	}
	/**
	 * 清理画布
	 */
	public void clear(){
		path.reset();//清除路径
		draw();//重置之后一定要记得调用draw方法，否则不能再次进行绘制
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		draw();// 肯定是在surfaceview开始时或者被改变的时候调用方法的执行
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	/**
	 * 复写onTouch方法，因为实现了接口
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		// 我觉得这个case的作用是找到第一次触摸的点
		case MotionEvent.ACTION_DOWN:// 处理一下按下事件,当按下的时候
			path.moveTo(event.getX(), event.getY());// 进行绘制,获取到按下点的坐标
			draw();//
			break;
		// 这个case的作用是在第一次触摸点上产生了移动，时刻拿到移动位置，时刻draw,事实上这个draw方法画的是一个点，然而在移动的过程中不停
		// 地
		// 执行这个case的draw方法，看上去就是连起来的一条线了，相当于就是在画一条线的周期中，moveTo方法只执行一次，而lineTo()执行多次
		case MotionEvent.ACTION_MOVE:
			path.lineTo(event.getX(), event.getY());
			draw();
			break;
		}
		return true;// 返回true，不然只能处理down事件
	}

}
