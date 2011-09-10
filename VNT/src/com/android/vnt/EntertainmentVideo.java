package com.android.vnt;

import android.app.Activity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class EntertainmentVideo extends Activity {
	/** Called when the activity is first created. */
	TextView entertainmentVidoTitleTextView;
	VideoView entertainmentVideoView;
	String strVideoPath;
	String TAG;
	boolean bIfSDExist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entertainmentvideo);
		settingsVarry();
		settingsButtonFun();

		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			bIfSDExist = true;
		} else {
			bIfSDExist = false;
			Toast.makeText(EntertainmentVideo.this,
					R.string.entertainmentErr_nosd_string, Toast.LENGTH_LONG)
					.show();
		}

	}

	private void settingsVarry() {
		strVideoPath = "/sdcard/test.mp4";
		TAG = "HIPPO_VIDEOVIEW";
		bIfSDExist = false;
		entertainmentVideoView = (VideoView) findViewById(R.id.entertainmentVideoView);
		entertainmentVidoTitleTextView = (TextView) findViewById(R.id.entertainmentVidoTitleTextView);

		playVideo(strVideoPath);
	}

	private void settingsButtonFun() {

	}

	private void playVideo(String strPath) {
		if (strPath != "") {
			entertainmentVideoView.setVideoURI(Uri.parse(strPath));
			entertainmentVideoView.setMediaController(new MediaController(
					EntertainmentVideo.this));
			entertainmentVideoView.requestFocus();
			entertainmentVideoView.start();
			entertainmentVideoView
					.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						public void onPrepared(MediaPlayer mp) {
							entertainmentVidoTitleTextView
									.setText("Now Playing:" + strVideoPath);
						}
					});
			entertainmentVideoView
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						public void onCompletion(MediaPlayer mp) {
							Toast.makeText(EntertainmentVideo.this,
									R.string.entertainmentPlayCompletionString,
									Toast.LENGTH_LONG).show();
						}
					});
			if (entertainmentVideoView.isPlaying()) {
				entertainmentVidoTitleTextView
						.setText("Now Playing:" + strPath);
				Log.i(TAG, strPath);
			}
		}
	}
}