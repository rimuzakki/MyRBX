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

public class UpdateKas extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Update, btn_Back;
    EditText et_NamaKas, et_TanggalKas, et_JumlahKas;
    RadioGroup rg_JenisKas;
    RadioButton rb_ItemsKas;
    TextView tv_IdKas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kas);

        dbHelper = new DataHelper(this);
        tv_IdKas = (TextView)findViewById(R.id.tvIdKas);
        et_NamaKas = (EditText)findViewById(R.id.etNamaKas);
        et_JumlahKas = (EditText)findViewById(R.id.etNominal);
        rg_JenisKas = (RadioGroup)findViewById(R.id.radioGroupJenisKas);
        et_TanggalKas = (EditText)findViewById(R.id.etTanggalKas);
        btn_Update = (Button)findViewById(R.id.btnUpdateKas);
        btn_Back = (Button)findViewById(R.id.btnBackKas);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kas WHERE id_kas = '" +
                getIntent().getStringExtra("detail") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tv_IdKas.setText(cursor.getString(0).toString());
            et_NamaKas.setText(cursor.getString(1).toString());
            et_JumlahKas.setText(cursor.getString(2).toString());

            String rb_value = cursor.getString(3).toString();
            if (rb_value.equals("pemasukan")) {
                rg_JenisKas.check(R.id.radioPemasukan);
            } else {
                rg_JenisKas.check(R.id.radioPengeluaran);
            }

            et_TanggalKas.setText(cursor.getString(4).toString());
        }

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectId = rg_JenisKas.getCheckedRadioButtonId();
                rb_ItemsKas = (RadioButton)findViewById(selectId);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE kas set nama_kas='"+
                        et_NamaKas.getText().toString() +"', jumlah_kas='" +
                        et_JumlahKas.getText().toString()+"', jenis_kas='"+
                        rb_ItemsKas.getText().toString() +"', tanggal_kas='" +
                        et_TanggalKas.getText().toString() + "' where id_kas='" +
                        tv_IdKas.getText().toString()+"'");

                Toast.makeText(getApplicationContext(), "Berhasil mengubah kas", Toast.LENGTH_LONG).show();
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
