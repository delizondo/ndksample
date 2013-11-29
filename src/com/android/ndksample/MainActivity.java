package com.android.ndksample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText mEditText;

	private Button mCalculateBtn;

	private TextView mJavaTimeLbl;

	private TextView mNativeTimeLbl;

	private static final String TAG = "MainActivity";

	static {
		System.loadLibrary("fibonacci");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mEditText = (EditText) findViewById(R.id.edittext);

		mCalculateBtn = (Button) findViewById(R.id.calculate_btn);

		mJavaTimeLbl = (TextView) findViewById(R.id.java_time_lbl);

		mNativeTimeLbl = (TextView) findViewById(R.id.native_time_lbl);

		mCalculateBtn.setOnClickListener(new CalculateClickListener());

	}

	private class CalculateClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			long value = Long.valueOf(TextUtils.isEmpty(mEditText.getText()
					.toString()) ? "0" : mEditText.getText().toString());

			new JavaAsyncCalculation().execute(value);
			new NativeAsyncCalculation().execute(value);

		}
	}

	private class NativeAsyncCalculation extends AsyncTask<Long, Void, Long> {

		private long mStartTime;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mNativeTimeLbl.setText(getString(R.string.loading));
			mStartTime = System.currentTimeMillis();
		}

		@Override
		protected Long doInBackground(Long... params) {
			long n = params[0];
			long result = -1;
			try {
				result = nativeFibonacci(n);
			} catch (UnsatisfiedLinkError e) {
				// Tried to run the native method in a not supported processor
				// architecture
				Log.e(TAG, e.getMessage(), e);
				result = javaFibonacci(n);
			}
			return result;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);

			long totalTime = System.currentTimeMillis() - mStartTime;
			mNativeTimeLbl.setText(getString(R.string.time_text, totalTime));

		}

	}

	private class JavaAsyncCalculation extends AsyncTask<Long, Void, Long> {
		private long mStartTime;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mJavaTimeLbl.setText(getString(R.string.loading));
			mStartTime = System.currentTimeMillis();
		}

		@Override
		protected Long doInBackground(Long... params) {
			long n = params[0];
			long result = javaFibonacci(n);
			return result;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);

			long totalTime = System.currentTimeMillis() - mStartTime;
			mJavaTimeLbl.setText(getString(R.string.time_text, totalTime));

		}
	}

	/**
	 * Definition of the native function to calculate the fibonacci value of N
	 * number
	 * 
	 * @param n
	 *            The value to calculate
	 * @return the fibonacci value of n
	 */
	public static native long nativeFibonacci(long n);

	/**
	 * Definition of the java function to calculate the fibonacci value of N
	 * number
	 * 
	 * @param n
	 *            The value to calculate
	 * @return the fibonacci value of n
	 */
	private long javaFibonacci(long n) {
		if (n > 1) {
			long n1 = 0, n2 = 1;
			do {
				long tmp = n2;
				n2 += n1;
				n1 = tmp;
			} while (--n > 1);
			return n2;
		}
		return n;
	}

}
