package com.android.vnt;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

//import java.io.IOException; 
//import android.media.AudioManager;
//import android.media.MediaPlayer; 
//import android.media.MediaPlayer.OnCompletionListener; 
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
//import android.widget.TextView; 
//import android.widget.Toast;

public class EntertainmentMusic extends Activity {
	/** Called when the activity is first created. */
	/*
	 * ImageButton mPause, mNext, mBefore, mStart, mStop; TextView mTextView;
	 * boolean bIsReleased; boolean bIsPaused; MediaPlayer mPlayer = new
	 * MediaPlayer();
	 */
	ImageButton mArtist, mAlbum, mSong, mPlaylist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entertainmentmusic2);
		settingsVarry();
		settingsButtonFun();

		/*
		 * Uri uri=Uri.parse("file:///sdcard/aa.mp3"); Intent i = new
		 * Intent(Intent.ACTION_VIEW); i.setDataAndType(uri, AUDIO_SERVICE);
		 * startActivity(i);
		 */
	}

	private void settingsVarry() {
		mArtist = (ImageButton) findViewById(R.id.entertainmentMusicArtistImageButton);
		mAlbum = (ImageButton) findViewById(R.id.entertainmentMusicAlbumImageButton);
		mSong = (ImageButton) findViewById(R.id.entertainmentMusicSongImageButton);
		mPlaylist = (ImageButton) findViewById(R.id.entertainmentMusicPlaylisImageButton);
		/*
		 * mStart = (ImageButton)
		 * findViewById(R.id.entertainmentMusicStartImageButton); mStop =
		 * (ImageButton) findViewById(R.id.entertainmentMusicStopImageButton);
		 * mPause = (ImageButton)
		 * findViewById(R.id.entertainmentMusicPauseImageButton); mNext =
		 * (ImageButton) findViewById(R.id.entertainmentMusicNextImageButton);
		 * mBefore = (ImageButton)
		 * findViewById(R.id.entertainmentMusicBeforImageButton); mTextView =
		 * (TextView) findViewById(R.id.entertainmentMusicTitleTextView);
		 * bIsPaused=false; bIsReleased=false;
		 */
	}

	private void settingsButtonFun() {
		mArtist.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_PICK);
					intent.setDataAndType(Uri.EMPTY,
							"vnd.android.cursor.dir/artistalbum");
					startActivity(intent);
				} catch (Exception e) {
					Log.e("mArtist.setOnClickListener", e.toString());
				}
			}
		});

		mAlbum.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_PICK);
					intent.setDataAndType(Uri.EMPTY,
							"vnd.android.cursor.dir/album");
					startActivity(intent);
				} catch (Exception e) {
					Log.e("mArtist.setOnClickListener", e.toString());
				}
			}
		});

		mSong.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_PICK);
					intent.setDataAndType(Uri.EMPTY,
							"vnd.android.cursor.dir/track");
					startActivity(intent);
				} catch (Exception e) {
					Log.e("mArtist.setOnClickListener", e.toString());
				}
			}
		});

		mPlaylist.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				try {
					// Intent intent = new Intent(Intent.ACTION_PICK);
					// intent.setDataAndType(Uri.EMPTY,
					// MediaStore.Audio.Playlists.CONTENT_TYPE);
					// startActivity(intent);
				} catch (Exception e) {
					Log.e("mArtist.setOnClickListener", e.toString());
				}
			}
		});

		/*
		 * mStart.setOnClickListener(new ImageButton.OnClickListener(){ public
		 * void onClick(View v) {
		 * mStart.setImageResource(R.drawable.entertainmentstarts);
		 * mPause.setImageResource(R.drawable.entertainmentpause); try {
		 * mPlayer.reset(); mPlayer.setDataSource( "/sdcard/aa.mp3" );
		 * mPlayer.prepare(); mPlayer.start();
		 * mTextView.setText("鎾斁锛�sdcard/aa.mp3"); } catch
		 * (IllegalStateException e) { mTextView.setText(e.toString());
		 * e.printStackTrace(); } catch (IOException e) {
		 * mTextView.setText(e.toString()); e.printStackTrace(); } } });
		 * 
		 * mPause.setOnClickListener(new ImageButton.OnClickListener(){ public
		 * void onClick(View view) { if (mPlayer != null) { if(bIsReleased ==
		 * false) { if(bIsPaused==false) { mPlayer.pause(); bIsPaused = true;
		 * mTextView.setText("鎾斁锛�--鏆傚仠锛�);
		 * mStart.setImageResource(R.drawable.entertainmentstart);
		 * mPause.setImageResource(R.drawable.entertainmentpause_2); } else
		 * if(bIsPaused==true) { mPlayer.start(); bIsPaused = false;
		 * mTextView.setText("鎾斁锛�----");
		 * mStart.setImageResource(R.drawable.entertainmentstarts);
		 * mPause.setImageResource(R.drawable.entertainmentpause); } } } } });
		 * 
		 * mNext.setOnClickListener(new ImageButton.OnClickListener(){ public
		 * void onClick(View arg0) {
		 * mStart.setImageResource(R.drawable.entertainmentstarts);
		 * mPause.setImageResource(R.drawable.entertainmentpause); try {
		 * mPlayer.reset(); mPlayer.setDataSource( "/sdcard/bb.mp3" );
		 * mPlayer.prepare(); mPlayer.start();
		 * mTextView.setText("鎾斁锛�sdcard/bb.mp3"); } catch
		 * (IllegalStateException e) { mTextView.setText(e.toString());
		 * e.printStackTrace(); } catch (IOException e) {
		 * mTextView.setText(e.toString()); e.printStackTrace(); } } });
		 * 
		 * mBefore.setOnClickListener(new ImageButton.OnClickListener(){ public
		 * void onClick(View arg0) {
		 * mStart.setImageResource(R.drawable.entertainmentstarts);
		 * mPause.setImageResource(R.drawable.entertainmentpause); try {
		 * mPlayer.reset(); mPlayer.setDataSource( "/sdcard/aa.mp3" );
		 * mPlayer.prepare(); mPlayer.start();
		 * mTextView.setText("鎾斁锛�sdcard/aa.mp3"); } catch
		 * (IllegalStateException e) { mTextView.setText(e.toString());
		 * e.printStackTrace(); } catch (IOException e) {
		 * mTextView.setText(e.toString()); e.printStackTrace(); } } });
		 * 
		 * mStop.setOnClickListener(new ImageButton.OnClickListener(){ public
		 * void onClick(View v) { if(mPlayer.isPlaying()==true) {
		 * mPlayer.reset(); mTextView.setText("鎾斁锛氬凡鍋滄鎾斁闊充箰锛�);
		 * mStart.setImageResource(R.drawable.entertainmentstart);
		 * mPause.setImageResource(R.drawable.entertainmentpause); } } });
		 * 
		 * mPlayer.setOnCompletionListener(new OnCompletionListener() { //
		 * @Override public void onCompletion(MediaPlayer arg0) {
		 * mTextView.setText("鎾斁锛�--瀹屾瘯锛�);
		 * mStart.setImageResource(R.drawable.entertainmentstart);
		 * mPause.setImageResource(R.drawable.entertainmentpause);
		 * Toast.makeText(EntertainmentMusic.this,
		 * R.string.entertainmentPlayMusicCompletionString
		 * ,Toast.LENGTH_LONG).show(); } });
		 */
	}

}