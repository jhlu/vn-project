package com.android.vnt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EmergencyInfoDatabase extends SQLiteOpenHelper
{
  private final static String DATABASE_NAME = "info.db";
  private final static int DATABASE_VERSION = 1;
  private final static String TABLE_NAME = "emergencyinfo";
  public  final static String FIELD_id = "_id";
  public  final static String FIELD_TITLE = "title";
  public  final static String FIELD_CONTENT = "content";
  public  final static String FIELD_TIME = "time";

  public EmergencyInfoDatabase(Context context)
  {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db)
  {
    String sql = "CREATE TABLE " + TABLE_NAME + " (" 
    	+ FIELD_id + " INTEGER primary key autoincrement, " 
    	+ FIELD_TITLE + " text, "
    	+ FIELD_CONTENT + " text, "
    	+ FIELD_TIME +" text)";
    db.execSQL(sql);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
  {
    String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
    db.execSQL(sql);
    onCreate(db);
  }

  public Cursor select()
  {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
    return cursor;
  }

  public long insert()
  {
	Log.e("1", "11");
    SQLiteDatabase db = this.getWritableDatabase();
	Log.e("222", "2222");
    ContentValues cv = new ContentValues(3);
	Log.e("3", "333");
    cv.put(FIELD_TITLE, "11111");
	Log.e("4", "44");
    cv.put(FIELD_CONTENT, "222222");
	Log.e("5", "55");
    cv.put(FIELD_TIME, "33333333");
	Log.e("6", "666");
    long row = db.insert(TABLE_NAME, null, cv);
	Log.e("7", "777");
    return row;
  }

  public void delete(int id)
  {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = FIELD_id + " = ?";
    String[] whereValue =
    { Integer.toString(id) };
    db.delete(TABLE_NAME, where, whereValue);
  }

  public void update(int id, String text)
  {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = FIELD_id + " = ?";
    String[] whereValue =
    { Integer.toString(id) };
    ContentValues cv = new ContentValues();
    cv.put(FIELD_TITLE, text);
    db.update(TABLE_NAME, cv, where, whereValue);
  }
}
