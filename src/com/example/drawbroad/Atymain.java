package com.example.drawbroad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Atymain extends Activity {
	private Button clearbtn;
	private Draw draw;// Ҫ���������϶�Ҫ�Ȼ�ȡ����ǰ��view���в���

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
