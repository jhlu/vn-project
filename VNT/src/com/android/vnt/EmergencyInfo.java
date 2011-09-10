package com.android.vnt;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class EmergencyInfo extends Activity {
	/** Called when the activity is first created. */
	ListView mListView;
	Cursor mCursor;
	Integer num;
	Intent infoIntent;
	Bundle infoBundle;
	DBHelper mOpenHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emergencyinfo);
		settingsVarry();
		settingsButtonFun();
		showItems();
	}

	private void settingsVarry() {
		mListView = (ListView) findViewById(R.id.emgergencyinfoListView);
		mOpenHelper = new DBHelper(this);  
		mCursor = mOpenHelper.queryEmergency();  
	}

	private void settingsButtonFun() {
		Button returnButton = (Button) findViewById(R.id.emergencyInfoReturnButton);
		returnButton.setOnClickListener(returnListener);
	}

	private void showItems() {
		num = mCursor.getCount();
		Log.e("3", num.toString());
		mCursor.moveToFirst();
		Log.e("4", mCursor.getString(0));
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.emergencyinfolist, mCursor,
				new String[] { "title", "time" }, new int[] {
						R.id.emergencyInfoListTitleTextView,
						R.id.emergencyInfoListTimeTextView });
		Log.e("5", "55");
		mListView.setAdapter(adapter);
		Log.e("6", "66");
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mCursor.moveToPosition(arg2);
				Log.e("7", "77");
				Log.e("mCursor.getString(0)", mCursor.getString(0));
				Log.e("mCursor.getString(1)", mCursor.getString(1));
				Log.e("mCursor.getString(2)", mCursor.getString(2));
				Log.e("mCursor.getString(3)", mCursor.getString(3));
				try {
					infoIntent = new Intent();
					infoBundle = new Bundle();
					Log.e("1", "1");
					infoIntent.setClass(EmergencyInfo.this,
							InfomationDetail.class);
					Log.e("2", "2");
					infoBundle.putString("KEY_TITLE", mCursor.getString(1)
							.toString());
					infoBundle.putString("KEY_CONTENT", mCursor.getString(2)
							.toString());
					infoBundle.putString("KEY_TIME", mCursor.getString(3)
							.toString());
					infoIntent.putExtras(infoBundle);
					Log.e("3", "3");
					startActivity(infoIntent);
					Log.e("4", "4");
				} catch (Exception e) {
					Log.e("mListView.setOnItemClickListener", e.toString());
				}
			}
		});
		mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Log.e("8", "88");
					}
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
	}

	private OnClickListener returnListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
}