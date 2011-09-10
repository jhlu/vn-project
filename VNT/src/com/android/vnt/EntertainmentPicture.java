package com.android.vnt;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class EntertainmentPicture extends Activity {
	/** Called when the activity is first created. */
	Button mPictureFind;
	ImageView mPictureImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entertainmentpicture);
		settingsVarry();
		settingsButtonFun();
	}

	private void settingsVarry() {
		mPictureFind = (Button) findViewById(R.id.entertainmentPictureFindButton);
		mPictureImageView = (ImageView) findViewById(R.id.entertainmentPictureImageView);
	}

	private void settingsButtonFun() {
		mPictureFind.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent i = new Intent(Intent.ACTION_GET_CONTENT);
					i.setType("image/*");
					startActivityForResult(i, 1);
				} catch (Exception e) {
					Log.e("mArtist.setOnClickListener", e.toString());
				}
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			ContentResolver cr = this.getContentResolver();
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(cr
						.openInputStream(uri));
				mPictureImageView.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}