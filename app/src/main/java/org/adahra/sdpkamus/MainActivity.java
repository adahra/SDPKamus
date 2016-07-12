package org.adahra.sdpkamus;

import android.app.Activity;
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
import android.widget.Toast;

public class MainActivity extends Activity {
	private String[] daftar = {"City", "Car"};
	private ListView listKamus;
	protected Cursor cursor;
	private SQLHelper dbHelper;
	public static MainActivity mainActivity;
	private Button btnTambah;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTambah = (Button) findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        mainActivity = this;
        
        dbHelper = new SQLHelper(this);

		try {
			dbHelper.buatDatabase();
		} catch (Exception ioe) {
			Toast.makeText(getApplicationContext(), "Gagal Membuat Database", Toast.LENGTH_LONG).show();
		}

		perbaruiDaftar();
    }
    
    public void perbaruiDaftar() {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

		cursor = db.rawQuery("SELECT * FROM kata", null);

		daftar = new String[cursor.getCount()];
		cursor.moveToFirst();

		for (int cc=0; cc < cursor.getCount(); cc++) {
			cursor.moveToPosition(cc);
			daftar[cc] = cursor.getString(1).toString();
		}

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