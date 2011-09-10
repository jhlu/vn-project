package com.android.vnt;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.pm.ResolveInfo;

public class Entertainment extends Activity {
	/** Called when the activity is first created. */
	Button returnButton;
	Button musicButton;
	Button videoButton;
	Button pictureButton;
	Button playgameButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entertainment);
		settingsVarry();
		settingsButtonFun();
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

	private void settingsVarry() {
		returnButton = (Button) findViewById(R.id.entertainmentReturnButton);
		musicButton = (Button) findViewById(R.id.entertainmentMusicButton);
		videoButton = (Button) findViewById(R.id.entertainmentVideoButton);
		pictureButton = (Button) findViewById(R.id.entertainmentPictureButton);
		playgameButton = (Button) findViewById(R.id.entertainmentPlaygameButton);
	}

	private void settingsButtonFun() {
		try {
			Log.i("c", "cc");
			returnButton.setOnClickListener(returnListener);
			Log.i("1", "1");
			musicButton.setOnClickListener(musicListener);
			Log.i("2", "2");
			videoButton.setOnClickListener(videoListener);
			Log.i("3", "3");
			pictureButton.setOnClickListener(pictureListener);
			Log.i("4", "4");
			playgameButton.setOnClickListener(playgameListener);
			Log.i("d", "dd");
		} catch (Exception e) {
			Log.i("settingsButtonFun", e.toString());
		}
	}

	private OnClickListener returnListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};

	private OnClickListener musicListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			ComponentName comp = new ComponentName("com.android.music",
					"com.android.music.MusicBrowserActivity");
			intent.setComponent(comp);
			intent.setAction("android.intent.action.VIEW");
			startActivity(intent);
		}
	};

	private OnClickListener videoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Entertainment.this, EntertainmentVideo.class);
			startActivity(intent);
		}
	};

	private OnClickListener pictureListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_MAIN, null);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			java.util.List<ResolveInfo> list = getPackageManager()
					.queryIntentActivities(intent, 0);
			findActivity(list, "gallery"); 
		}
	};

	private OnClickListener playgameListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
}