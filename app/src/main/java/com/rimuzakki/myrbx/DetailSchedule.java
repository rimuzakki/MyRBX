package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailSchedule extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_BackSchedule;
    TextView tv_IdEvent, tv_NamaEvent, tv_JenisEvent, tv_TanggalEvent, tv_JamEvent, tv_TempatEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule);

        dbHelper = new DataHelper(this);
        tv_IdEvent = (TextView)findViewById(R.id.tvIdEvent);
        tv_NamaEvent = (TextView)findViewById(R.id.tvNamaEvent);
        tv_JenisEvent = (TextView)findViewById(R.id.tvJenisEvent);
        tv_TanggalEvent = (TextView)findViewById(R.id.tvTanggalEvent);
        tv_JamEvent = (TextView)findViewById(R.id.tvJamEvent);
        tv_TempatEvent = (TextView)findViewById(R.id.tvTempatEvent);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM event WHERE id_event = '" +
                getIntent().getStringExtra("detail") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tv_IdEvent.setText(cursor.getString(0).toString());
            tv_NamaEvent.setText(cursor.getString(1).toString());
            tv_JenisEvent.setText(cursor.getString(2).toString());
            tv_TanggalEvent.setText(cursor.getString(3).toString());
            tv_JamEvent.setText(cursor.getString(4).toString());
            tv_TempatEvent.setText(cursor.getString(5).toString());
        }

        btn_BackSchedule = (Button)findViewById(R.id.btnBackDetailSchedule);
        btn_BackSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
