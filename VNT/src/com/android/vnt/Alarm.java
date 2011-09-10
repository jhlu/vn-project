package com.android.vnt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Alarm extends Activity {
	/** Called when the activity is first created. */
	TextView gpsTextView;
	JNIReadGPS jniReadGPS;

	static {
		System.loadLibrary("jniReadGPS");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);
		settingsVarry();
		settingsButtonFun();
	}

	private void settingsVarry() {
		jniReadGPS = new JNIReadGPS();
		gpsTextView = (TextView) findViewById(R.id.alarmGPSInfoTextView);
		gpsTextView.setText(jniReadGPS.readGPS());
	}

	private void sendToServer() {
		// TODO,send the GPS message to Server
	}

	private void settingsButtonFun() {
		Button sendButton = (Button) findViewById(R.id.alarmSendButton);
		sendButton.setOnClickListener(sendListener);
		Button returnButton = (Button) findViewById(R.id.alarmReturnButton);
		returnButton.setOnClickListener(returnListener);
	}

	private OnClickListener sendListener = new OnClickListener() {
		public void onClick(View v) {
			sendToServer();
			Toast.makeText(Alarm.this,
					R.string.alarmSend_nosd_string, Toast.LENGTH_LONG)
					.show();
		}
	};

	private OnClickListener returnListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
}