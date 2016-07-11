package com.aar.android.sdp.dbsqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity {
	protected Cursor cursor;
	SQLHelper dbHelper;

	Button btnEditSimpan;
	EditText edEditNama;
	EditText edEditLatitude;
	EditText edEditLongitude;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        
        dbHelper = new SQLHelper(this);
        
        edEditNama = (EditText) findViewById(R.id.edEditNama);
        edEditLatitude = (EditText) findViewById(R.id.edEditLatitude);
        edEditLongitude = (EditText) findViewById(R.id.edEditLongitude);
        
        SQLiteDatabase db = dbHelper.getReadableDatabase();

		cursor = db.rawQuery("SELECT * FROM kota WHERE nama = '" + getIntent().getStringExtra("nama") + "'",null);

		cursor.moveToFirst();
		if (cursor.getCount()>0)
		{
			cursor.moveToPosition(0);
			edEditNama.setText(cursor.getString(1).toString());
			edEditLatitude.setText(cursor.getString(2).toString());
			edEditLongitude.setText(cursor.getString(3).toString());
		}
        
        btnEditSimpan = (Button) findViewById(R.id.btnEditSimpan);
		// daftarkan even onClick pada btnSimpan
        btnEditSimpan.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.execSQL("update kota SET nama='" + edEditNama.getText().toString()+"', " +
						"latitude='"+ edEditLatitude.getText().toString() +"', " +
								"longitude='" + edEditLongitude.getText().toString() + "' WHERE " +
							    " nama = '" + getIntent().getStringExtra("nama") + "'");
				Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
				// MainActivity.ma.RefreshList();
				finish();
			}
			
		});

    }
}
