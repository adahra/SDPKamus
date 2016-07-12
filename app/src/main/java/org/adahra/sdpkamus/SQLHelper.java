package org.adahra.sdpkamus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "kamus.db";
	private static final int DATABASE_VERSION = 1;
	private static String DB_PATH = "/data/data/org.adahra.sdpkamus/databases/";
	private static final String TABLE = "kata";
	private Context mContext;

	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void buatDatabase(SQLiteDatabase sqlData) {
		String sql = "create table kata(id integer primary key autoincrement, inggris varchar(200) null, " +
				"indonesia varchar(200) null, keterangan text null);";
		Log.d("Data", "onCreate: " + sql);
		sqlData.execSQL(sql);
		sql = "INSERT INTO kata (id, inggris, indonesia, keterangan) VALUES (1, 'City', 'Kota', 'Daerah pusat pemukiman');";
		sqlData.execSQL(sql);
		sql = "INSERT INTO kata (id, inggris, indonesia, keterangan) VALUES (2, 'Car', 'Mobil', 'Kendaraan roda empat');";
		sqlData.execSQL(sql);
	}

	public void buatDatabase() throws IOException {
		if(dataBaseisExist()){
			Toast.makeText(mContext, "Database Sudah Ada", Toast.LENGTH_LONG).show();
		} else {
			this.getReadableDatabase();

			try {
				copyDataBase();
				Toast.makeText(mContext, "Database Berhasil Diimport Dari Assets", Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	private boolean dataBaseisExist() {
		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		} catch(SQLiteException e) {

		}

		if(checkDB != null) {
			checkDB.close();
		}

		if(checkDB != null ) {
			return true ;
		} else {
			return false;
		}
	}

	private void copyDataBase() throws IOException {
		InputStream myInput = mContext.getAssets().open(DATABASE_NAME);
		String outFileName = DB_PATH + DATABASE_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;

		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
}
