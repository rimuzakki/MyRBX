package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailMember extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_BackDetailMember;
    TextView tv_IdMember, tv_NamaLengkap, tv_Alamat, tv_Notelp, tv_TempatLahir, tv_TanggalLahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_member);

        dbHelper = new DataHelper(this);
        tv_IdMember = (TextView)findViewById(R.id.tvIdMember);
        tv_NamaLengkap = (TextView)findViewById(R.id.tvNamaLengkap);
        tv_Alamat = (TextView)findViewById(R.id.tvAlamat);
        tv_Notelp = (TextView)findViewById(R.id.tvNoTelp);
        tv_TempatLahir = (TextView)findViewById(R.id.tvTempatLahir);
        tv_TanggalLahir = (TextView)findViewById(R.id.tvTanggalLahir);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM member WHERE id_member = '" +
                getIntent().getStringExtra("detail_member") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            tv_IdMember.setText(cursor.getString(0).toString());
            tv_NamaLengkap.setText(cursor.getString(1).toString());
            tv_Alamat.setText(cursor.getString(2).toString());
            tv_Notelp.setText(cursor.getString(3).toString());
            tv_TempatLahir.setText(cursor.getString(4).toString());
            tv_TanggalLahir.setText(cursor.getString(5).toString());
        }

        btn_BackDetailMember = (Button)findViewById(R.id.btnBackDetailMember);
        btn_BackDetailMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
