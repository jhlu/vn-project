package com.android.vnt;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Handler;
import android.os.Message;

import com.alipay.app.SampleActivity;

public class Vnt extends Activity {
	/** Called when the activity is first created. */
	public String currentOperate;
	boolean isRunning = false;
	private DBHelper mDbHelper;
	Cursor mCursor;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Eula.show(this);
		mDbHelper = new DBHelper(getApplicationContext());
		setContentView(R.layout.main);
		settingsVarry();
		settingsButtonFun();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Thread background = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					Log.e("dbhelper", "00");
					addDataInfo();
					receiverFromServer();
				} catch (InterruptedException e) {

				}
				handler.sendMessage(handler.obtainMessage());
			}
		});
		isRunning = true;
		background.start();
	}

	private void settingsVarry() {
		currentOperate = getString(R.string.vntCurrentOperate);
	}

	private final void findActivity(java.util.List<ResolveInfo> list, String key) {
		if (list == null || key == null) {
			return;
		}
		String findKey = key.toLowerCase();
		Object object = null;
		ResolveInfo info = null;
		for (int i = 0, count = list.size(); i < count; i++) {
			object = list.get(i);
			if (object instanceof ResolveInfo) {
				info = (ResolveInfo) object;
				if (info.activityInfo.name == null) {
					continue;
				}
				if (info.activityInfo.name.toLowerCase().indexOf(findKey) > -1) {
					System.out.println(info.activityInfo.packageName + "  "
							+ info.activityInfo.name);
					tryLaunch(info.activityInfo.packageName,
							info.activityInfo.name);
					break;
				}
			}
		}
	}

	private final void tryLaunch(String packageName, String name) {
		if (packageName == null || name == null) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_VIEW, null);
		intent.setClassName(packageName, name);
		try {
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void settingsButtonFun() {
		Button vntAlarmButton = (Button) findViewById(R.id.vntAlarmButton);
		vntAlarmButton.setOnClickListener(alarmListener);
		Button vntNavigationButton = (Button) findViewById(R.id.vntNavigationButton);
		vntNavigationButton.setOnClickListener(navigationListener);
		Button vntPhoneButton = (Button) findViewById(R.id.vntPhoneButton);
		vntPhoneButton.setOnClickListener(phoneListener);
		Button vntOperatorServerButton = (Button) findViewById(R.id.vntEntertainmentButton);
		vntOperatorServerButton.setOnClickListener(entertainmentListener);
		Button vntEmergencyInfoButton = (Button) findViewById(R.id.nvtEmergencyInfoButton);
		vntEmergencyInfoButton.setOnClickListener(emergencyInfoListener);
		Button vntEasyInfoButton = (Button) findViewById(R.id.vntEasyInfoButton);
		vntEasyInfoButton.setOnClickListener(easyInfoListener);
		Button vntInternetButton = (Button) findViewById(R.id.nvtInternetButton);
		vntInternetButton.setOnClickListener(internetListener);
		Button vntEmailButton = (Button) findViewById(R.id.vntEmailButton);
		vntEmailButton.setOnClickListener(emailListener);
		Button vntMaintenanceButton = (Button) findViewById(R.id.vntMaintenanceButton);
		vntMaintenanceButton.setOnClickListener(maintenanceListener);
		Button vntSettingsButton = (Button) findViewById(R.id.vntSettingsButton);
		vntSettingsButton.setOnClickListener(settingsListener);
	}

	private OnClickListener alarmListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Vnt.this, Alarm.class);
			startActivity(intent);
			Log.i(currentOperate, "enter the page of alarm");
		}
	};

	private OnClickListener navigationListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Vnt.this, Navigation.class);
			startActivity(intent);
			Log.i(currentOperate, "enter the page of navigation");
		}
	};

	private OnClickListener phoneListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Vnt.this, Phone.class);
			startActivity(intent);
			Log.i(currentOperate, "enter the page of phone");
		}
	};

	private OnClickListener entertainmentListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Vnt.this, Entertainment.class);
			startActivity(intent);
			Log.i(currentOperate, "enter the page of operatorServer");
		}
	};

	private OnClickListener emergencyInfoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Vnt.this, EmergencyInfo.class);
			startActivity(intent);
			Log.i(currentOperate, "enter the page of emergencyInfo");
		}
	};

	private OnClickListener easyInfoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Vnt.this, EasyInfo.class);
			startActivity(intent);
			Log.i(currentOperate, "enter the page of easyInfo");
		}
	};

	private OnClickListener internetListener = new OnClickListener() {
		public void onClick(View v) {
			Uri uri = Uri.parse("http://www.taobao.com/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			Log.i(currentOperate, "enter the page of internet");
		}
	};

	private OnClickListener emailListener = new OnClickListener() {
		public void onClick(View v) {

			Intent intent = new Intent(Intent.ACTION_MAIN, null);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			java.util.List<ResolveInfo> list = getPackageManager()
					.queryIntentActivities(intent, 0);
			findActivity(list, "email");
			Log.i(currentOperate, "enter the page of email");
		}
	};

	private OnClickListener maintenanceListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Vnt.this, Maintenance.class);
			startActivity(intent);
			Log.i(currentOperate, "enter the page of maintenance");
		}
	};

	private OnClickListener settingsListener = new OnClickListener() {
		public void onClick(View v) {
			Log.i(currentOperate, "enter the page of settings 0");
			Intent intent = new Intent();
			Log.i(currentOperate, "enter the page of settings 1");
			intent.setClass(Vnt.this, SampleActivity.class);
			Log.i(currentOperate, "enter the page of settings 2");
			startActivity(intent);
			Log.i(currentOperate, "enter the page of settings 3");
		}
	};

	private void addDataInfo() {
		mDbHelper = new DBHelper(this);
		mCursor = mDbHelper.query();
		if (mCursor.getCount() < 6) {
			// add emergency info 
			mDbHelper.insert("Emergency Infomation a",
							"emergency infomation a emergency infomation a emergency infomation a",
							"1");
			mDbHelper.insert("Emergency Infomation b",
							"emergency infomation b emergency infomation b emergency infomation b",
							"1");
			mDbHelper.insert("Emergency Infomation c",
							"emergency infomation c emergency infomation c emergency infomation c",
							"1");
			mDbHelper.insert("Emergency Infomation d",
							"emergency infomation d emergency infomation d emergency infomation d",
							"1");
			mDbHelper.insert("Emergency Infomation e",
							"emergency infomation e emergency infomation e emergency infomation e",
							"1");
			mDbHelper.insert("Emergency Infomation f",
							"emergency infomation f emergency infomation f emergency infomation f",
							"1");
			mDbHelper.insert("Emergency Infomation g",
							"emergency infomation g emergency infomation g emergency infomation g",
							"1");
			mDbHelper.insert("Emergency Infomation h",
							"emergency infomation h emergency infomation h emergency infomation h",
							"1");
			mDbHelper.insert("Emergency Infomation i",
							"emergency infomation i emergency infomation i emergency infomation i",
							"1");

			// add easy info
			mDbHelper.insert("Easy Infomation 1",
							"easy infomation 1 easy infomation 1 easy infomation 1 easy infomation 1",
							"2");
			mDbHelper.insert("Easy Infomation 2",
							"easy infomation 2 easy infomation 2 easy infomation 2 easy infomation 2",
							"2");
			mDbHelper.insert("Easy Infomation 3",
							"easy infomation 3 easy infomation 3 easy infomation 3 easy infomation 3",
							"2");
			mDbHelper.insert("Easy Infomation 4",
							"easy infomation 4 easy infomation 4 easy infomation 4 easy infomation 4",
							"2");
			mDbHelper.insert("Easy Infomation 5",
							"easy infomation 5 easy infomation 5 easy infomation 5 easy infomation 5",
							"2");
			mDbHelper.insert("Easy Infomation 6",
							"easy infomation 6 easy infomation 6 easy infomation 6 easy infomation 6",
							"2");
			mDbHelper.insert("Easy Infomation 7",
							"easy infomation 7 easy infomation 7 easy infomation 7 easy infomation 7",
							"2");
			mDbHelper.insert("Easy Infomation 8",
							"easy infomation 8 easy infomation 8 easy infomation 8 easy infomation 8",
							"2");
			mDbHelper.insert("Easy Infomation 9",
							"easy infomation 9 easy infomation 9 easy infomation 9 easy infomation 9",
							"2");
		} else {

		}
	}

	private void receiverFromServer() {

	}
}