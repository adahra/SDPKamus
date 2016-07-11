package com.aar.android.sdp.dbsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "kamus.db";
	private static final int DATABASE_VERSION = 1;

	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table kata( id integer primary key autoincrement, inggris varchar(200) null, " +
				"indonesia varchar(200) null, keterangan text null);";
		Log.d("Data", "onCreate: " + sql);
		db.execSQL(sql);
		sql = "INSERT INTO kata (id, inggris, indonesia, keterangan) VALUES (1, 'City', 'Kota', 'Daerah pusat pemukiman');";
		db.execSQL(sql);
		sql = "INSERT INTO kata (id, inggris, indonesia, keterangan) VALUES (2, 'Car', 'Mobil', 'Kendaraan roda empat');";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		
	}

}
