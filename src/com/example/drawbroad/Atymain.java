package com.example.drawbroad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Atymain extends Activity {
	private Button clearbtn;
	private Draw draw;// 要清理画布，肯定要先获取到当前的view进行操作

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atymain);
		draw = (Draw) findViewById(R.id.drawbroad);
		findViewById(R.id.clearbtn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				draw.clear();
			}
		});
	}

}
