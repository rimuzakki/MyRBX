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

public class KasActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Simpan, btn_Back;
    EditText et_NamaKas, et_JumlahKas, et_TanggalKas;
    RadioGroup rg_JenisKas;
    RadioButton rb_ItemsKas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kas);

        dbHelper = new DataHelper(this);
        et_NamaKas = (EditText)findViewById(R.id.etNamaKas);
        et_JumlahKas = (EditText)findViewById(R.id.etNominal);
        rg_JenisKas = (RadioGroup)findViewById(R.id.radioGroupJenisKas);
        et_TanggalKas = (EditText)findViewById(R.id.etTanggalKas);
        btn_Simpan = (Button)findViewById(R.id.btnSimpanKas);
        btn_Back = (Button)findViewById(R.id.btnBackKas);

        btn_Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectId = rg_JenisKas.getCheckedRadioButtonId();
                rb_ItemsKas = (RadioButton)findViewById(selectId);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into kas(nama_kas, jumlah_kas, jenis_kas, tanggal_kas) VALUES('" +

                        et_NamaKas.getText().toString() + "','" +
                        et_JumlahKas.getText().toString() + "','" +
                        rb_ItemsKas.getText().toString() + "','" +
                        et_TanggalKas.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil menambah kas", Toast.LENGTH_LONG).show();
                ListKas.ma.RefreshList();
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
