package com.android.vnt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Phone extends Activity {
	/** Called when the activity is first created. */
	Button returnButton;
	Button phoneGeneralButton;
	Button phoneOperatorButton;
	Button phoneVolumeUpButton;
	Button phoneVolumeDownButton;
	ProgressBar phoneVolumeProgress;
	String phoneGeneralCallNumber;
	String phoneOperatorCallNumber;
	AudioManager phoneAudioMa;
	int phoneVolume;
	int phoneMaxVolume;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone);
		settingsVarry();
		setPhoneVolume();
		settingsButtonFun();
	}

	private void settingsVarry() {
		phoneAudioMa = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		phoneVolumeProgress = (ProgressBar) findViewById(R.id.myProgress);
		phoneVolume = 0;
	}

	private void setPhoneVolume() {
		try {
			phoneMaxVolume = phoneAudioMa
					.getStreamMaxVolume(AudioManager.STREAM_RING);
			phoneVolumeProgress.setMax(phoneMaxVolume);
			phoneVolume = phoneAudioMa
					.getStreamVolume(AudioManager.STREAM_RING);
			phoneVolumeProgress.setProgress(phoneVolume);
		} catch (Exception e) {
			Log.e("setPhoneVolume", e.toString());
		}
	}

	private void settingsButtonFun() {
		returnButton = (Button) findViewById(R.id.phoneReturnButton);
		returnButton.setOnClickListener(returnListener);
		phoneGeneralButton = (Button) findViewById(R.id.phoneGeneralButton);
		phoneGeneralButton.setOnClickListener(phoneListener);
		phoneOperatorButton = (Button) findViewById(R.id.phoneOperatorButton);
		phoneOperatorButton.setOnClickListener(phoneListener);
		phoneVolumeUpButton = (Button) findViewById(R.id.phoneVolumeUpButton);
		phoneVolumeUpButton.setOnClickListener(phoneListener);
		phoneVolumeDownButton = (Button) findViewById(R.id.phoneVolumeDownButton);
		phoneVolumeDownButton.setOnClickListener(phoneListener);
	}

	private OnClickListener returnListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
	private OnClickListener phoneListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.phoneGeneralButton:
				generalPhoneFun();
				break;
			case R.id.phoneOperatorButton:
				operatorPhoneFun();
				break;
			case R.id.phoneVolumeUpButton:
				volumeUp();
				break;
			case R.id.phoneVolumeDownButton:
				volumeDown();
				break;
			default:
				break;
			}
		}
	};

	private void volumeUp() {
		try {
			phoneAudioMa.adjustVolume(AudioManager.ADJUST_RAISE, 0);
			phoneVolume = phoneAudioMa
					.getStreamVolume(AudioManager.STREAM_RING);
			phoneVolumeProgress.setProgress(phoneVolume);
		} catch (Exception e) {
			Log.e("volumeUp", e.toString());
		}
	}

	private void volumeDown() {
		try {
			phoneAudioMa.adjustVolume(AudioManager.ADJUST_LOWER, 0);
			phoneVolume = phoneAudioMa
					.getStreamVolume(AudioManager.STREAM_RING);
			phoneVolumeProgress.setProgress(phoneVolume);
		} catch (Exception e) {
			Log.e("volumeDown", e.toString());
		}
	}

	private void generalPhoneFun() {
		try {
			/*
			 * phoneGeneralCallNumber=phoneGeneralEdittext.getText().toString();
			 * if
			 * (PhoneNumberUtils.isGlobalPhoneNumber(phoneGeneralCallNumber)){
			 * Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel://" +
			 * phoneGeneralCallNumber)); startActivity(i); } else {
			 * Toast.makeText(Phone.this,
			 * R.string.phone_Notify_incorrect_phonenumber_string
			 * ,Toast.LENGTH_LONG).show(); }
			 */
			Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://"));
			// Intent i = new Intent(Intent., Uri.parse("tel://" ));
			startActivity(i);
		} catch (Exception e) {
			Log.e("generalPhoneFun", e.toString());
		}
	}

	private void operatorPhoneFun() {
		try {
			phoneOperatorCallNumber = getString(R.string.phoneOperatorNumberString);
			if (PhoneNumberUtils.isGlobalPhoneNumber(phoneOperatorCallNumber)) {
				Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel://"
						+ phoneOperatorCallNumber));
				startActivity(i);
				Toast.makeText(Phone.this,
						R.string.phone_Notify_operator_string,
						Toast.LENGTH_LONG).show();
			} else {
				// Toast.makeText(Phone.this,
				// R.string.phone_Notify_incorrect_phonenumber_string,Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("operatorPhoneFun", e.toString());
		}

	}
}