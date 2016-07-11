package com.aar.android.sdp.dbsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	String[] daftar = {"City", "Car"};
	ListView listKamus;
	Menu menu;
	protected Cursor cursor;
	SQLHelper dbHelper;
	public static MainActivity mainActivity;
	private Button btnTambah;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mainActivity = this;
        
        dbHelper = new SQLHelper(this);

		perbaruiDaftar();
    }
    
    public void perbaruiDaftar() {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

		cursor = db.rawQuery("SELECT * FROM kata",null);

		daftar = new String[cursor.getCount()];
		cursor.moveToFirst();

		for (int cc=0; cc < cursor.getCount(); cc++) {
			cursor.moveToPosition(cc);
			daftar[cc] = cursor.getString(1).toString();
		}

		btnTambah = (Button) findViewById(R.id.btnTambah);
		btnTambah.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, AddActivity.class);
				startActivity(intent);
			}
		});

    	listKamus = (ListView)findViewById(R.id.listKamus);
        listKamus.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listKamus.setSelected(true);
        
        ((ArrayAdapter) listKamus.getAdapter()).notifyDataSetInvalidated();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

}