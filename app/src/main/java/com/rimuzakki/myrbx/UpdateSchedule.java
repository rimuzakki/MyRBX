package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateSchedule extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Update, btn_Back;
    EditText et_NamaEvent, et_TanggalEvent, et_JamEvent, et_TempatEvent;
    RadioGroup rg_JenisEvent;
    RadioButton rb_ItemsEvent, rb_Latihan, rb_Event, rb_Turnamen;
    TextView tv_IdEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_schedule);

        dbHelper = new DataHelper(this);
        tv_IdEvent = (TextView)findViewById(R.id.tvIdEvent);
        et_NamaEvent = (EditText)findViewById(R.id.etNamaEvent);
        rg_JenisEvent = (RadioGroup)findViewById(R.id.radioGroupJenisEvent);
        et_TanggalEvent = (EditText)findViewById(R.id.etTanggalEvent);
        et_JamEvent = (EditText)findViewById(R.id.etJamEvent);
        et_TempatEvent = (EditText)findViewById(R.id.etTempatEvent);
        btn_Update = (Button)findViewById(R.id.btnUpdateSchedule);
        btn_Back = (Button)findViewById(R.id.btnBackSchedule);


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM event WHERE id_event = '" +
                getIntent().getStringExtra("detail") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tv_IdEvent.setText(cursor.getString(0).toString());
            et_NamaEvent.setText(cursor.getString(1).toString());

            String rb_value = cursor.getString(2).toString();
            if (rb_value.equals("Latihan")) {
                rg_JenisEvent.check(R.id.radioLatihan);
            } else if (rb_value.equals("Event")) {
                rg_JenisEvent.check(R.id.radioEvent);
            } else {
                rg_JenisEvent.check(R.id.radioTurnamen);
            }

            et_TanggalEvent.setText(cursor.getString(3).toString());
            et_JamEvent.setText(cursor.getString(4).toString());
            et_TempatEvent.setText(cursor.getString(5).toString());
        }

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectId = rg_JenisEvent.getCheckedRadioButtonId();
                rb_ItemsEvent = (RadioButton)findViewById(selectId);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE event set nama_event='"+
                        et_NamaEvent.getText().toString() +"', jenis_event='" +
                        rb_ItemsEvent.getText().toString()+"', tanggal_event='"+
                        et_TanggalEvent.getText().toString() +"', jam_event='" +
                        et_JamEvent.getText().toString() + "' where id_event='" +
                        tv_IdEvent.getText().toString()+"'");

                Toast.makeText(getApplicationContext(), "Berhasil mengubah event", Toast.LENGTH_LONG).show();
                ListSchedule.ma.RefreshList();
                finish();
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
