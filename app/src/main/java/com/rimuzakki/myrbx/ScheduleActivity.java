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
import android.widget.Toast;

public class ScheduleActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Simpan, btn_Back;
    EditText et_NamaEvent, et_TanggalEvent, et_JamEvent, et_TempatEvent;
    RadioGroup rg_JenisEvent;
    RadioButton rb_ItemsEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        dbHelper = new DataHelper(this);
        et_NamaEvent = (EditText)findViewById(R.id.etNamaEvent);
        rg_JenisEvent = (RadioGroup)findViewById(R.id.radioGroupJenisEvent);
        et_TanggalEvent = (EditText)findViewById(R.id.etTanggalEvent);
        et_JamEvent = (EditText)findViewById(R.id.etJamEvent);
        et_TempatEvent = (EditText)findViewById(R.id.etTempatEvent);
        btn_Simpan = (Button)findViewById(R.id.btnSimpanSchedule);
        btn_Back = (Button)findViewById(R.id.btnBackSchedule);

        btn_Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                int selectId = rg_JenisEvent.getCheckedRadioButtonId();
                rb_ItemsEvent = (RadioButton)findViewById(selectId);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into event(nama_event, jenis_event, tanggal_event, jam_event, tempat_event) VALUES('" +

                        et_NamaEvent.getText().toString() + "','" +
                        rb_ItemsEvent.getText().toString() + "','" +
                        et_TanggalEvent.getText().toString() + "','" +
                        et_JamEvent.getText().toString() + "','" +
                        et_TempatEvent.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil menambah event", Toast.LENGTH_LONG).show();
                ListMember.ma.RefreshList();
                finish();
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
//                Toast.makeText(ScheduleActivity.this, rb_ItemsEvent.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
