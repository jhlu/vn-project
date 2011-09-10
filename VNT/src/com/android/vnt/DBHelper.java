package com.android.vnt;

import java.util.Calendar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "info.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "infomation";
	private static final String ID = "_id";
	private static final String TITLE = "title";
	private static final String BODY = "body";
	private static final String TIME = "time";
	private static final String FLAG = "flag";

	String sql = "CREATE TABLE " + TABLE_NAME + " (" + ID
			+ " INTEGER primary key autoincrement, " + TITLE
			+ " text not null, " + BODY + " text not null, " + TIME
			+ " text not null, " + FLAG + " text not null" + ");";

	private SQLiteDatabase db;

	DBHelper(Context c) {
		super(c, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("dbhelper", "33");
		this.db = db;
		db.execSQL(sql);
	}

	public void insert(String title, String body, String flag) {
		
		Log.e("dbhelper", "44");
		SQLiteDatabase db = getWritableDatabase();
		Log.e("dbhelper", "55");
		ContentValues initialValues = new ContentValues();
		initialValues.put(TITLE, title);
		initialValues.put(BODY, body);
		Calendar calendar = Calendar.getInstance();
		String created = calendar.get(Calendar.YEAR) + ":"
				+ calendar.get(Calendar.MONTH) + ":"
				+ calendar.get(Calendar.DAY_OF_MONTH) + ","
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND);
		initialValues.put(TIME, created);
		initialValues.put(FLAG, flag);
		Log.e("dbhelper", "66");
		db.insert(TABLE_NAME, null, initialValues);
		db.close();
		Log.e("dbhelper", "77");
	}

	public Cursor query() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
		return c;
	}
	
	public Cursor queryEmergency() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(true, TABLE_NAME, new String[] {
				ID, TITLE, BODY, TIME, FLAG }, FLAG + "="
				+ "1", null, null, null, null, null);
		return c;
	}
	
	public Cursor queryEasy() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(true, TABLE_NAME, new String[] {
				ID, TITLE, BODY, TIME, FLAG }, FLAG + "="
				+ "2", null, null, null, null, null);
		return c;
	}
	
	public void del(int id) {
		if (db == null)
			db = getWritableDatabase();
		db.delete(TABLE_NAME, "_id=?", new String[] { String.valueOf(id) });
	}

	public void close() {
		if (db != null)
			db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
}