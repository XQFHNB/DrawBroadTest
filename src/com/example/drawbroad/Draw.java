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

/**���� ��������ַ�����һ�����ڻ����н�������һ�����ڻ����н�������
 * 
 */
public class Draw extends SurfaceView implements Callback, OnTouchListener {
	private Paint pen = new Paint();// ׼������
	private Path path = new Path();// ���л��ʲ��У����ǻ�Ҫ��·��

	/**
	 * �Զ���ؼ�����Ĺ��췽�������췽���п϶����ֶν��г�ʼ��
	 * 
	 * @param context
	 * @param attrs
	 */
	public Draw(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this);// ����ʹ��surfaceview�Ϳ϶�Ҫ���һ���ص�����
		pen.setColor(Color.RED);// ���û�����ɫ
		pen.setTextSize(10);// ���û��������С
		pen.setStyle(Style.STROKE);// ���û��ʵķ����ʵ�Ļ��ǿ��ĵģ����ľ���STROKE
		setOnTouchListener(this);
	}

	/**
	 * ����һ�����Ʒ��������������������Ҫ���ƿ϶���Ҫ��һ����������˰��չ�������õ�һ��canvas
	 */
	public void draw() {
		Canvas canvas = getHolder().lockCanvas();// ��Ϊʵ��surfaceview�У����������Ҫͨ��getholder����������
		canvas.drawColor(Color.WHITE);// ���ȳ�ʼ����������ɫ�����ǿ��Գ�ʼ��Ϊ��ɫ
		canvas.drawPath(path, pen);// ��������ͨ��������������л��ƣ���һ������Ϊdrawpath���󣬵ڶ�������Ϊ���ʶ���
		getHolder().unlockCanvasAndPost(canvas);// ������ɺ�����Ҫ��������
	}
	/**
	 * ������
	 */
	public void clear(){
		path.reset();//���·��
		draw();//����֮��һ��Ҫ�ǵõ���draw�������������ٴν��л���
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		draw();// �϶�����surfaceview��ʼʱ���߱��ı��ʱ����÷�����ִ��
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
	 * ��дonTouch��������Ϊʵ���˽ӿ�
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		// �Ҿ������case���������ҵ���һ�δ����ĵ�
		case MotionEvent.ACTION_DOWN:// ����һ�°����¼�,�����µ�ʱ��
			path.moveTo(event.getX(), event.getY());// ���л���,��ȡ�����µ������
			draw();//
			break;
		// ���case���������ڵ�һ�δ������ϲ������ƶ���ʱ���õ��ƶ�λ�ã�ʱ��draw,��ʵ�����draw����������һ���㣬Ȼ�����ƶ��Ĺ����в�ͣ
		// ��
		// ִ�����case��draw����������ȥ������������һ�����ˣ��൱�ھ����ڻ�һ���ߵ������У�moveTo����ִֻ��һ�Σ���lineTo()ִ�ж��
		case MotionEvent.ACTION_MOVE:
			path.lineTo(event.getX(), event.getY());
			draw();
			break;
		}
		return true;// ����true����Ȼֻ�ܴ���down�¼�
	}

}
