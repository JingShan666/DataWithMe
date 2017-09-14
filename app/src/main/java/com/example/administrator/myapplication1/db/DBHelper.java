package com.example.administrator.myapplication1.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{

	public DBHelper(Context context) {
		super(context, "info.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// TODO Auto-generated method stub
		String photoSQL = "CREATE TABLE photoFile(photo_id varchar(10) primary key , " +
				"photoUrl varchar(20), " +
        		"photoNum varchar(20))";
		
		String allPhotoSQL = "CREATE TABLE photoList(allPhoto_id varchar(10) primary key , " +
        		"allPhotoUrl varchar(20))";
		db.execSQL(photoSQL);
		Log.d("my", "create table photoSQL:"+photoSQL);
		db.execSQL(allPhotoSQL);
		Log.d("my", "create table allPhotoSQL:"+allPhotoSQL);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub
		
	}

}
