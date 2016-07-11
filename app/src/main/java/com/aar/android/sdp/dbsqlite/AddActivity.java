package com.aar.android.sdp.dbsqlite;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {
	private EditText etTambahInggris;
	private EditText etTambahIndonesia;
	private EditText etTambahKeterangan;
	private Button btnTambahSimpan;

	private SQLHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        
        dbHelper = new SQLHelper(this);
        
		etTambahInggris = (EditText) findViewById(R.id.etTambahInggris);
		etTambahIndonesia = (EditText) findViewById(R.id.etTambahIndonesia);
		etTambahKeterangan = (EditText) findViewById(R.id.etTambahKeterangan);
		btnTambahSimpan = (Button) findViewById(R.id.btnTambahSimpan);
		btnTambahSimpan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.execSQL("insert into kata(inggris, indonesia, keterangan) values('" +
						etTambahInggris.getText().toString()+ "','"+
						etTambahIndonesia.getText().toString() +"','" +
						etTambahKeterangan.getText().toString() + "')");
				Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.mainActivity.perbaruiDaftar();
				finish();
			}
		});
    }
}
