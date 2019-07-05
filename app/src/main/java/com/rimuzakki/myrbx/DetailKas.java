package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailKas extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Back;
    TextView tv_IdKas, tv_NamaKas, tv_JumlahKas, tv_JenisKas, tv_TanggalKas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kas);

        dbHelper = new DataHelper(this);
        tv_IdKas = (TextView)findViewById(R.id.tvIdKas);
        tv_NamaKas = (TextView)findViewById(R.id.tvNamaKas);
        tv_JumlahKas = (TextView)findViewById(R.id.tvNominal);
        tv_JenisKas = (TextView)findViewById(R.id.tvJenisKas);
        tv_TanggalKas = (TextView)findViewById(R.id.tvTanggalKas);
        btn_Back = (Button)findViewById(R.id.btnBackDetailKas);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kas WHERE id_kas = '" +
                getIntent().getStringExtra("detail") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tv_IdKas.setText(cursor.getString(0).toString());
            tv_NamaKas.setText(cursor.getString(1).toString());
            tv_JumlahKas.setText(cursor.getString(2).toString());
            tv_JenisKas.setText(cursor.getString(3).toString());
            tv_TanggalKas.setText(cursor.getString(4).toString());
        }

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
