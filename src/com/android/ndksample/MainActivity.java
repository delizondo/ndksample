package com.android.ndksample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mNativeResult;

	static {
		System.loadLibrary("fibonacci");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNativeResult = (TextView) findViewById(R.id.native_result);

		mNativeResult.setText(String.valueOf(nativeFibonacci(6)));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static native long nativeFibonacci(int n);

}
